package cl.uchile.dcc.scrabble.gui;

import AST.NodeType;
import AST.NodoOperation.Add;
import AST.NodoOperation.And;
import AST.NodoOperation.Numerical.Div;
import AST.NodoOperation.Numerical.Mult;
import AST.NodoOperation.Numerical.Plus;
import AST.NodoOperation.Numerical.Subs;
import AST.NodoOperation.Or;
import Factory.ActualType.MakeBool;
import Factory.ActualType.MakeString;
import Factory.ActualType.Number.MakeBinary;
import Factory.ActualType.Number.MakeFloat;
import Factory.ActualType.Number.MakeInt;
import Type.ActualType.Number.ActualNumber.TypeBinary;
import Type.ActualType.Number.ActualNumber.TypeFloat;
import Type.ActualType.Number.ActualNumber.TypeInt;
import Type.ActualType.TypeBool;
import Type.ActualType.TypeString;
import Type.IType;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 */
public class App extends Application {
    private static final String RESOURCE_PATH = "src/main/resources/";
    private double unit = 35.6; //el largo de un cuadro, se usara simplemente para mantener un orden

    /*
    Se le dara uso a las factories de la tarea pasada
    Estas cumplen el objetivo de ahorrar memoria
    Pero tambien son sencillas  de manejar
     */
    private MakeString StringMaker = new MakeString();
    private MakeBool BoolMaker = new MakeBool();
    private MakeInt IntMaker = new MakeInt();
    private MakeFloat FloatMaker = new MakeFloat();
    private MakeBinary BinaryMaker = new MakeBinary();

    /*
    La creacion de los siguientes tipos se debe exlusivamente
    Al dise;o particular de esta tarea
     */
    private NodeType RESULT = null; //almacena el ultimo resultado o el primer objeto
    private NodeType OBJECT = null; //almacena el ultimo objeto creado
    private int state = 0; //define el estado, 0 si no hemos creado objetos, 1 si ya creamos un objeto
    private String VRes = ""; //y la visualizacion del resultado
    private String VObj = ""; //visualizacion del objeto

    /*
    Variables que se usaran para almacenar datos del programa con la funcion "save" de la aplicacion
    Estas funciones estan representadas con el boton Sn, donde n es un numero
     */
    private int[] save = new int[7];
    private NodeType[] saves = new NodeType[7];
    private String[] Vsaves = new String[7];

    @Override
    public void start(@NotNull Stage stage) throws FileNotFoundException{
        /*
        Para la visualizacion
         */
        stage.setTitle("Calculadora DORA"); //Nombre de la aplicacion
        var root = new Group(); //creamos e inicializamos nuestro grupo
        var scene = new Scene(root, unit*8, unit*8); //creamos el escenario
        var background = new ImageView(new Image(new FileInputStream(RESOURCE_PATH + "cuadricula.png")));
        root.getChildren().add(background); //agregamos el fondo

        /*
        El resultado y el input estara en tres recuadros
        Estos recuadros estan definidos como metodos mas abajo
        Esto para mantener cierto orden y solo llamarlos
        Esta parte del codigo estara mas centrada en los "EventHandler"
         */
        var inputRight = inputTextRight(); //El recuadro inferior, donde se visualizaran las operaciones
        var inputLeft = inputTextLeft(); //El recuadro que se encuentra en el centro, nuestro input
        var tResult = tResult(); //Y el recuadro superior, donde se visualizara la respuesta

        /*
        Los botones tambien estan definidos como metodos mas abajo
         */
        Button bAdd = buttonADD(); //Boton de nuestra operacion ADD
        EventHandler<ActionEvent> clickADD = new EventHandler<ActionEvent>() { //al hacer click
            @Override
            public void handle(ActionEvent actionEvent) {
                RESULT = new Add(RESULT, OBJECT); //Esta es la base de la respuesta, operaremos nuestro resultado
                //Con el ultimo objeto creado

                VRes = "Add("+VRes+","+VObj+")"; //se crea la visualizacion de la operacion de forma recursiva
                OBJECT = null;
                VObj = "";
                inputRight.setText(VRes); //se visualiza
            }
        };
        bAdd.setOnAction(clickADD); //y se setea nuestro click para el boton de la operacion

        Button bAnd = buttonAND(); //para la operacion AND
        EventHandler<ActionEvent> clickAND = new EventHandler<ActionEvent>() { //al hacer click
            @Override
            public void handle(ActionEvent actionEvent) {
                RESULT = new And(RESULT, OBJECT); //se opera
                VRes = "And("+VRes+","+VObj+")"; //se crea la visualizacion de la operacion de forma recursiva
                OBJECT = null;
                VObj = "";
                inputRight.setText(VRes); //se visualiza
            }
        };
        bAnd.setOnAction(clickAND); //se setea el click para el boton
        Button bOr = buttonOR();
        EventHandler<ActionEvent> clickOR = new EventHandler<ActionEvent>() { //al hacer click
            @Override
            public void handle(ActionEvent actionEvent) {
                RESULT = new Or(RESULT, OBJECT); //se opera
                VRes = "Or("+VRes+","+VObj+")"; //se crea la visualizacion de la operacion de forma recursiva
                OBJECT = null;
                VObj = "";
                inputRight.setText(VRes); //se visualiza
            }
        };
        bOr.setOnAction(clickOR); //se setea el click para el boton
        Button bPlus = buttonPlus();
        EventHandler<ActionEvent> clickPLUS = new EventHandler<ActionEvent>() { //al hacer click
            @Override
            public void handle(ActionEvent actionEvent) {
                RESULT = new Plus(RESULT, OBJECT); //se opera
                VRes = "Plus("+VRes+","+VObj+")"; //se crea la visualizacion de la operacion de forma recursiva
                OBJECT = null;
                VObj = "";
                inputRight.setText(VRes); //se visualiza
            }
        };
        bPlus.setOnAction(clickPLUS); //se setea el click para el boton
        Button bSubs = buttonSubs();
        EventHandler<ActionEvent> clickSUBS = new EventHandler<ActionEvent>() { //al hacer click
            @Override
            public void handle(ActionEvent actionEvent) {
                RESULT = new Subs(RESULT, OBJECT); //se opera
                VRes = "Subs("+VRes+","+VObj+")"; //se crea la visualizacion de la operacion de forma recursiva
                OBJECT = null;
                VObj = "";
                inputRight.setText(VRes); //se visualiza
            }
        };
        bSubs.setOnAction(clickSUBS); //se setea el click para el boton
        Button bMult = buttonMult();
        EventHandler<ActionEvent> clickMULT = new EventHandler<ActionEvent>() { //al hacer click
            @Override
            public void handle(ActionEvent actionEvent) {
                RESULT = new Mult(RESULT, OBJECT); //se opera
                VRes = "Mult("+VRes+","+VObj+")"; //se crea la visualizacion de la operacion de forma recursiva
                OBJECT = null;
                VObj = "";
                inputRight.setText(VRes); //se visualiza
            }
        };
        bMult.setOnAction(clickMULT); //se setea el click para el boton
        Button bDiv = buttonDiv();
        EventHandler<ActionEvent> clickDIV = new EventHandler<ActionEvent>() { //al hacer click
            @Override
            public void handle(ActionEvent actionEvent) {
                RESULT = new Div(RESULT, OBJECT); //se opera
                VRes = "Div("+VRes+","+VObj+")"; //se crea la visualizacion de la operacion de forma recursiva
                OBJECT = null;
                VObj = "";
                inputRight.setText(VRes); //se visualiza
            }
        };
        bDiv.setOnAction(clickDIV); //se setea el click para el boton

        /*
        Botones para crear cada tipo
         */
        Button bString = bString(); //creamos e inicializamos el boton para crear Strings, es el mas sencillo
        EventHandler<ActionEvent> clickString = new EventHandler<ActionEvent>() { //se crea el evento
            @Override
            public void handle(ActionEvent actionEvent) {
                if(state==0) { //si todavia estamos en el estado inicial
                    RESULT = StringMaker.simpleMake(new TypeString(inputLeft.getText())); //entonces RESULT
                    //almacenara nuestro nuevo objeto
                    VRes = "String("+inputLeft.getText()+")"; //la visualizacion
                    inputRight.setText(VRes); //y se entrega al usuario
                    inputLeft.setText("String created! ..."); //se da aviso de la operacion
                    state = 1; //salimos del estado inicial
                }
                else{ //en el caso de que no estemos en el estado inicial
                    OBJECT = StringMaker.simpleMake(new TypeString(inputLeft.getText())); //OBJECT almacenara
                    //nuestro nuevo objeto
                    VObj = "String("+inputLeft.getText()+")"; // la visualizacion
                    inputRight.setText(VObj); //se entrega al usuario
                    inputLeft.setText("String created! ..."); //se da aviso de que la operacion fue realizada con exito
                }
            }
        };
        bString.setOnAction(clickString); //se setea la accion

        Button bBool = bBool(); //Inicializamos el boton para crear Bool
        EventHandler<ActionEvent> clickBool = new EventHandler<ActionEvent>() {//creamos el evento clickBool
            @Override
            public void handle(ActionEvent actionEvent) {
                String bool = inputLeft.getText(); //para hacer mas simple el codigo almacenamos nuestro input en un string
                boolean[] bCheck = checkBool(bool); //y luego llamamos al metodo que se ha creado especificamente
                //para determinar si un string se puede transformar a bool, y si es asi entonces que bool
                if(bCheck[0]){ //bCheck[0] nos indica si es bool
                    if (state == 0) {
                        RESULT = BoolMaker.simpleMake(new TypeBool(bCheck[1])); //y bCheck[1] nos indica el bool
                        VRes = "Bool(" + bool + ")";
                        inputRight.setText(VRes);
                        inputLeft.setText("Bool created! ...");
                        state = 1;
                    } else { //en caso de que no entonces:
                        OBJECT = BoolMaker.simpleMake(new TypeBool(bCheck[1]));
                        VObj = "Bool(" + bool + ")";
                        inputRight.setText(VObj);
                        inputLeft.setText("Bool created! ...");

                    }
                }
                else{
                    inputLeft.setText("Please, create a valid Bool");
                }
            }
        };
        bBool.setOnAction(clickBool); //seteamos la accion
        Button bInt = bInt(); //creamos e inicializamos el boton para crear int
        EventHandler<ActionEvent> clickInt = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //En este caso usaremos un try y un catch
                //Para intentar crear un int a partir del texto ingresado
                try{
                    int integer = Integer.valueOf(inputLeft.getText()); //si es posible
                    if(state == 0){ //entonces replicamos las soluciones anteriores
                        RESULT = IntMaker.simpleMake(new TypeInt(integer));
                        VRes = "Int(" + inputLeft.getText() + ")";
                        inputRight.setText(VRes);
                        inputLeft.setText("Int created! ...");
                        state = 1;
                    }
                    else{
                        OBJECT = IntMaker.simpleMake(new TypeInt(integer));
                        VObj = "Int(" + inputLeft.getText() + ")";
                        inputRight.setText(VRes);
                        inputLeft.setText("Int created! ...");
                    }
                }
                catch (Exception e){ //y para cuando no sea posible
                    inputLeft.setText("Please, create a valid Int");
                }
            }
        };
        bInt.setOnAction(clickInt); //seteamos la accion del boton

        Button bFloat = bFloat(); //Para el boton Float seguimos el mismo principio
        EventHandler<ActionEvent> clickFloat = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    double flt = Double.valueOf(inputLeft.getText());
                    if(state == 0){
                        RESULT = FloatMaker.simpleMake(new TypeFloat(flt));
                        VRes = "Float(" + inputLeft.getText() + ")";
                        inputRight.setText(VRes);
                        inputLeft.setText("Float created! ...");
                        state = 1;
                    }
                    else{
                        OBJECT = FloatMaker.simpleMake(new TypeFloat(flt));
                        VObj = "Float(" + inputLeft.getText() + ")";
                        inputRight.setText(VRes);
                        inputLeft.setText("Float created! ...");
                    }
                }
                catch (Exception e){
                    inputLeft.setText("Please, create a valid Float");
                }
            }
        };
        bFloat.setOnAction(clickFloat);
        Button bBinary = bBinary(); //creamos e inicializamos nuestro boton para crear Binary
        EventHandler<ActionEvent> clickBinary = new EventHandler<ActionEvent>() { //En este caso el evento es distinto
            @Override
            public void handle(ActionEvent actionEvent) { //no es posible usar try ya que efectivamente
                //es posible crear un Binary con un string cualquiera
                //esto se podria solucionar alterando el TypeBinary
                //y esto evitaria futuros posibles problemas entorno a la creacion de binarios con strings cualquiera
                //ESA ES LA MEJOR SOLUCION
                //Sin embargo es mucho mas sencillo, al menos por el momento
                //Crear un metodo que similar al boolCheck que nos indique si el string es un string binario o no lo es
                //y a partir de este metodo crear un TypeBinary, y eso es lo que se hara
                //Reitero que no es la mejor solucion, pero por el momento es la mas rapida y sencilla
                boolean bCheck = checkBinary(inputLeft.getText());
                if(bCheck){ //con esto el proceso es homologo a un try y un catch
                    //el resto es identico al codigo usado anteriormente
                    if(state == 0){
                        RESULT = BinaryMaker.simpleMake(new TypeBinary(inputTextLeft().getText()));
                        VRes = "Binary(" + inputLeft.getText() + ")";
                        inputRight.setText(VRes);
                        inputLeft.setText("Binary created! ...");
                        state = 1;
                    }
                    else{
                        OBJECT = BinaryMaker.simpleMake(new TypeBinary(inputTextLeft().getText()));
                        VObj = "Binary(" + inputLeft.getText() + ")";
                        inputRight.setText(VRes);
                        inputLeft.setText("Binary created! ...");
                    }
                }
                else{
                    inputLeft.setText("Please, create a valid Binary");
                }
            }
        };
        bBinary.setOnAction(clickBinary); //se setea la accion del click


        Button bC = buttonC(); //Creamos el boton "c" que sera el boton que "reseteara" los datos
        //de la interfaz
        EventHandler<ActionEvent> clickC = new EventHandler<ActionEvent>() { //definimos el evento
            @Override
            public void handle(ActionEvent actionEvent) {
                //Creamos el reset, esto es:
                RESULT = null;
                VRes ="";
                OBJECT = null;
                VObj = "";
                state = 0;
                //Y entregamos un feedback al usuario
                tResult.setText("Result...");
                inputLeft.setText("Cleaning 100%!");
                inputRight.setText("Visualization");
            }
        };
        bC.setOnAction(clickC); //seteamos la accion del boton

        Button bCalc = bCalculate(); //para el boton de calcular
        //se utilizara un try y un catch
        EventHandler<ActionEvent> clickCalculate = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try { //el try intentara calcular el resultado de las operaciones
                    if (RESULT == null) {
                        //para el caso de que el objeto sea null
                        //reseteamos todo
                        tResult.setText("Syntax Error");
                        inputLeft.setText("Please, try again...");
                        inputRight.setText("Visualization");
                        //el reset esta definido por estos parametros, como en "c"
                        RESULT = null;
                        VRes = "";
                        OBJECT = null;
                        VObj = "";
                        state = 0;
                    }
                    if (RESULT.calculate() == null) { //idem
                        tResult.setText("Syntax Error");
                        inputLeft.setText("Please, try again...");
                        inputRight.setText("Visualization");
                        RESULT = null;
                        VRes = "";
                        OBJECT = null;
                        VObj = "";
                        state = 0;
                    } else {
                        //si
                        tResult.setText("RESULT: " + (RESULT.calculate()).toString());

                    }
                }
                catch (Exception e){ //y si no es posible entonces
                    tResult.setText("Syntax Error");
                    inputLeft.setText("Please, try again...");
                    inputRight.setText("Visualization");
                    //definimos un reset para evitar un "loop" de problemas
                    //o que el usuario deba usar "c"
                    RESULT = null;
                    VRes = "";
                    OBJECT = null;
                    VObj = "";
                    state = 0;
                }
            }

        };
        bCalc.setOnAction(clickCalculate); //seteamos la accion del boton

        /*
        SAVE PROFILE
        Estos botones seran usados para guardar resultados anteriores
        TODOS LOS BOTONES SAVE SON IDENTICOS
         */
        //inicializamos las variables
        save[1] = 0; save[2] = 0; save[3] = 0; save[4] = 0; save[5] = 0; save[6] = 0;
        Button save1 = save1(); //todos los botones save son identicos
        EventHandler<ActionEvent> click1 = new EventHandler<ActionEvent>() { //creamos el evento
            @Override
            public void handle(ActionEvent actionEvent) {
                if(save[1]==0){ //si el save no contiene nada
                    if(RESULT != null) { //y nuestro resultado no es vacio
                        saves[1] = RESULT; //guardamos
                        Vsaves[1] = VRes; //tambien guardamos la visualizacion
                        save[1] = 1; //y ahora el save contiene algo
                        inputLeft.setText("Result saved to S1"); //feedback para el usario
                    }
                    else{
                        //de lo contrario no hacemos nada
                        //y le damos un feedback al usuario
                        inputLeft.setText("Nothing to import or export! Task uncompleted...");

                    }
                }
                else{ //si hay algo en el save significa que queremos recuperar ese algo
                    OBJECT = saves[1]; //recuperamos
                    VObj = Vsaves[1]; //recuperamos
                    saves[1] = null; //y ahora limpiamos los datos del save
                    Vsaves[1] = ""; //limpiamos
                    save[1] = 0; //y ahora el save no contiene nada
                    inputLeft.setText("S1 imported! ..."); //feedback para el usuario
                }
            }
        };
        save1.setOnAction(click1); //seteamos la accion

        Button save2 = save2(); //idem
        EventHandler<ActionEvent> click2 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(save[2]==0){
                    if(RESULT != null) {
                        saves[2] = RESULT;
                        Vsaves[2] = VRes;
                        save[2] = 1;
                        inputLeft.setText("Result saved to S2");
                    }
                    else{
                        inputLeft.setText("Nothing to import or export! Task uncompleted...");
                    }
                }
                else{
                    OBJECT = saves[2];
                    VObj = Vsaves[2];
                    saves[2] = null;
                    Vsaves[2] = "";
                    save[2] = 0;
                    inputLeft.setText("S2 imported! ...");
                }
            }
        };
        save2.setOnAction(click2);
        Button save3 = save3();
        EventHandler<ActionEvent> click3 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(save[3]==0){
                    if(RESULT != null) {
                        saves[3] = RESULT;
                        Vsaves[3] = VRes;
                        save[3] = 1;
                        inputLeft.setText("Result saved to S3");
                    }
                    else{
                        inputLeft.setText("Nothing to import or export! Task uncompleted...");
                    }
                }
                else{
                    OBJECT = saves[3];
                    VObj = Vsaves[3];
                    saves[3] = null;
                    Vsaves[3] = "";
                    save[3] = 0;
                    inputLeft.setText("S3 imported! ...");
                }
            }
        };
        save3.setOnAction(click3);
        Button save4 = save4();
        EventHandler<ActionEvent> click4 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(save[4]==0){
                    if(RESULT != null) {
                        saves[4] = RESULT;
                        Vsaves[4] = VRes;
                        save[4] = 1;
                        inputLeft.setText("Result saved to S4");
                    }
                    else{
                        inputLeft.setText("Nothing to import or export! Task uncompleted...");
                    }
                }
                else{
                    OBJECT = saves[4];
                    VObj = Vsaves[4];
                    saves[4] = null;
                    Vsaves[4] = "";
                    save[4] = 0;
                    inputLeft.setText("S4 imported! ...");
                }
            }
        };
        save4.setOnAction(click4);
        Button save5 = save5();
        EventHandler<ActionEvent> click5 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(save[5]==0){
                    if(RESULT != null) {
                        saves[5] = RESULT;
                        Vsaves[5] = VRes;
                        save[5] = 1;
                        inputLeft.setText("Result saved to S5");
                    }
                    else{
                        inputLeft.setText("Nothing to import or export! Task uncompleted...");
                    }
                }
                else{
                    OBJECT = saves[5];
                    VObj = Vsaves[5];
                    saves[5] = null;
                    Vsaves[5] = "";
                    save[5] = 0;
                    inputLeft.setText("S5 imported! ...");
                }
            }
        };
        save5.setOnAction(click5);
        Button save6 = save6();
        EventHandler<ActionEvent> click6 = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(save[6]==0){
                    if(RESULT != null) {
                        saves[6] = RESULT;
                        Vsaves[6] = VRes;
                        save[6] = 1;
                        inputLeft.setText("Result saved to S6");
                    }
                    else{
                        inputLeft.setText("Nothing to import or export! Task uncompleted...");
                    }
                }
                else{
                    OBJECT = saves[6];
                    VObj = Vsaves[6];
                    saves[6] = null;
                    Vsaves[6] = "";
                    save[6] = 0;
                    inputLeft.setText("S6 imported! ...");
                }
            }
        };
        save6.setOnAction(click6);

        Button dSave = deleteSave(); //este boton limpia todo lo que este almacenado en los save
        EventHandler<ActionEvent> clickDelete = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(save[0]==0){ //primeros arrojamos un warning para evitar que todo se elimine de casualidad
                    inputLeft.setText("Press (!) again to delete saves...");
                    save[0]=1;
                }
                else{
                    //si se vuelve a presionar eliminamos todo
                    save[1] = 0; save[2] = 0; save[3] = 0; save[4] = 0; save[5] = 0; save[6] = 0;
                    saves[1] = null; saves[2] = null; saves[3] = null; saves[4] = null; saves[5] = null; saves[6] = null;
                    Vsaves[1] = ""; Vsaves[2] = ""; Vsaves[3] = ""; Vsaves[4] = ""; Vsaves[5] = ""; Vsaves[6] = "";
                    save[0]=0; //y dejamos el boton depresionado nuevamente
                    inputLeft.setText("ALL SAVES DELETED"); //feeedback al usuario de lo que acaba de ocurrir
                }
            }
        };
        dSave.setOnAction(clickDelete); //seteamos la accion del boton

        /*
        Terminamos de setear el escenario cargando todas las variables que se usaran

         */


        root.getChildren().add(inputRight);
        root.getChildren().add(inputLeft);

        root.getChildren().add(bAdd);
        root.getChildren().add(bOr);
        root.getChildren().add(bAnd);
        root.getChildren().add(bPlus);
        root.getChildren().add(bSubs);
        root.getChildren().add(bMult);
        root.getChildren().add(bDiv);

        root.getChildren().add(bString);

        root.getChildren().add(bBool);
        root.getChildren().add(bInt);
        root.getChildren().add(bFloat);
        root.getChildren().add(bBinary);

        root.getChildren().add(bC);
        root.getChildren().add(bCalc);

        root.getChildren().add(tResult);

        root.getChildren().add(save1);
        root.getChildren().add(save2);
        root.getChildren().add(save3);
        root.getChildren().add(save4);
        root.getChildren().add(save5);
        root.getChildren().add(save6);
        root.getChildren().add(dSave);

        stage.setScene(scene);
        stage.show();

    }
    /*
    BOTONES
    Cada metodo se encarga de crear un boton en particular
    y setear su posicion y tama;o, nada mas
     */
    private @NotNull Button buttonADD(){
        var button = new Button("ADD");
        button.setLayoutX(unit-10);
        button.setLayoutY(unit*5-10);
        button.setPrefSize(100+unit,20);
        return button;
    }
    private @NotNull Button buttonOR(){
        var button = new Button("OR");
        button.setLayoutX(unit-10);
        button.setLayoutY(unit*6-10);
        button.setPrefSize(40+unit/2,20);
        //ACCION
        return button;
    }
    private @NotNull Button buttonAND(){
        var button = new Button("AND");
        button.setLayoutX(unit+unit/2+50);
        button.setLayoutY(unit*6-10);
        button.setPrefSize(40+unit/2,20);
        return button;
    }
    private @NotNull Button buttonPlus(){
        var button = new Button("+");
        button.setLayoutX(unit-10);
        button.setLayoutY(unit*7-10);
        return button;
    }
    private @NotNull Button buttonSubs(){
        var button = new Button("-");
        button.setLayoutX(unit*5/4+25);
        button.setLayoutY(unit*7-10);
        return button;
    }
    private @NotNull Button buttonMult(){
        var button = new Button("*");
        button.setLayoutX(unit*6/4+50);
        button.setLayoutY(unit*7-10);
        return button;
    }
    private @NotNull Button buttonDiv(){
        var button = new Button("/");
        button.setLayoutX(unit*2+70);
        button.setLayoutY(unit*7-10);
        return button;
    }
    private @NotNull Button buttonC(){
        var button = new Button("C");
        button.setLayoutX(unit*5-10);
        button.setLayoutY(unit*4-10);
        button.setPrefSize(20,20);
        return button;
    }
    private @NotNull Button bCalculate(){
        var button = new Button("=");
        button.setLayoutX(unit*5-10);
        button.setLayoutY(unit*3-10);
        return button;
    }

    private @NotNull TextField inputTextRight(){
        var input = new TextField("Visualization");
        input.setLayoutX(unit-10);
        input.setLayoutY(unit*4-10);
        input.setPrefSize(100+unit,20);
        return input;
    }
    private @NotNull TextField inputTextLeft(){
        var input = new TextField("INPUT");
        input.setLayoutX(unit-10);
        input.setLayoutY(unit*3-10);
        input.setPrefSize(100+unit,20);
        return input;
    }
    private @NotNull Button bString(){
        var button = new Button("String");
        button.setLayoutX(unit*6-10);
        button.setLayoutY(unit*3-10);
        button.setPrefSize(unit*2-10,20);
        return button;
    }
    private @NotNull Button bBool(){
        var button = new Button("Boolean");
        button.setLayoutX(unit*6-10);
        button.setLayoutY(unit*4-10);
        button.setPrefSize(unit*2-10,20);
        return button;
    }
    private @NotNull Button bInt(){
        var button = new Button("Int");
        button.setLayoutX(unit*6-10);
        button.setLayoutY(unit*5-10);
        button.setPrefSize(unit*2-10,20);
        return button;
    }
    private @NotNull Button bFloat(){
        var button = new Button("Float");
        button.setLayoutX(unit*6-10);
        button.setLayoutY(unit*6-10);
        button.setPrefSize(unit*2-10,20);
        return button;
    }
    private @NotNull Button bBinary(){
        var button = new Button("Binary");
        button.setLayoutX(unit*6-10);
        button.setLayoutY(unit*7-10);
        button.setPrefSize(unit*2-10,20);
        return button;
    }
    private @NotNull TextField tResult(){
        var text = new TextField("Result");
        text.setLayoutX(unit-10);
        text.setLayoutY(unit*2-10);
        text.setPrefSize(unit*7-10,20);
        text.setDisable(false);
        return text;
    }
    private @NotNull Button save1(){
        var button = new Button("S1");
        button.setLayoutX(unit-10);
        button.setLayoutY(unit-10);
        return button;
    }
    private @NotNull Button save2(){
        var button = new Button("S2");
        button.setLayoutX(unit*2-10);
        button.setLayoutY(unit-10);
        return button;
    }
    private @NotNull Button save3(){
        var button = new Button("S3");
        button.setLayoutX(unit*3-10);
        button.setLayoutY(unit-10);
        return button;
    }
    private @NotNull Button save4(){
        var button = new Button("S4");
        button.setLayoutX(unit*4-10);
        button.setLayoutY(unit-10);
        return button;
    }
    private @NotNull Button save5(){
        var button = new Button("S5");
        button.setLayoutX(unit*5-10);
        button.setLayoutY(unit-10);
        return button;
    }
    private @NotNull Button save6(){
        var button = new Button("S6");
        button.setLayoutX(unit*6-10);
        button.setLayoutY(unit-10);
        return button;
    }
    private @NotNull Button deleteSave(){
        var button = new Button("!");
        button.setLayoutX(unit*7-10);
        button.setLayoutY(unit-10);
        button.setPrefSize(unit-10,20);
        return button;
    }

    /*
    Los siguientes metodos son usados para checkear un string
     */
    private @NotNull boolean[] checkBool(String Str){ //retorna en [0] si el string puede convertirse a bool
        //y en [1] que bool seria este (notar que si [0] es falso, [1] tambien
        int count = 0;
        boolean[] result = new boolean[2];
        //inicializamos el resultado
        result[0] = false;
        result[1] = false;
        String f = "false";
        String t = "true";
        if(Str.charAt(count) == 'f'){
            count++;
            while(count<5){ //revisamos si concide con false
                if(Str.charAt(count)!=f.charAt(count)){//si no, entonces retornamos false,false
                    return result;
                }
                count++;
            }
            if(Str.length()!=5){ //si no coincide en largo
                return result; //retornamos false, false
            }
            result[0] = true;
            result[1] = false;
            return result; //si coincide con false retornamos true,false
        }
        if(Str.charAt(count) == 't'){ //idem
            while(count<4){
                if(Str.charAt(count)!=t.charAt(count)){
                    return result;
                }
                count++;
            }
            if(Str.length()!=4){
                return result;
            }
            result[0] = true;
            result[1] = true;
            return result; //si coincide con true retornamos true,true
        }
        else{
            return result; //de lo contrario retornamos false, false
        }
    }
    private @NotNull boolean checkBinary(String str){
        if(str.length()>32){ //esto se debe a que nuestros binarios tienen 32bits
            return false;
        }
        int count = 0;
        while(count!=str.length()){ //revisamos
            if(str.charAt(count)!='0' && str.charAt(count)!='1'){ //si hay algo distinto de 0 o 1
                return false; //no es binario -> retornamos false
            }
            count++;
        }
        return true; //de lo contrario es binario y retornamos true
    }


    public static void main(String[] args) {
        launch();
    }
    /*
    Eso fue todo!
    Espero que mis explicaciones se hayan entendido bien
    Decidi no separar en partes la interfaz porque tuve problemas en un inicio haciendo las cosas por separado
    Y para garantizar la estabilidad del programa tuve que realizarlo de esta forma
    Es problable que hayan sido errores mios, sin embargo estoy relativamente conforme con el
    "mediano" orden.

    Muchas gracias por su tiempo!         
     */

}