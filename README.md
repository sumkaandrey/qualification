# qualification
public class DataBaseHandler {
Connection dbConnection;
Statement statement;
ResultSet resultSet;
final static DataBaseHandler dataBaseHandler = new DataBaseHandler();

public static DataBaseHandler getDataBaseHandler(){return dataBaseHandler;}

public Connection getDbConnection() throws ClassNotFoundException, SQLException {
String connectionString = "jdbc:mysql://localhost:3306/qualification";
Class.forName("com.mysql.cj.jdbc.Driver");
dbConnection = DriverManager.getConnection(connectionString, "root", "1234");
return dbConnection;
}
public int[] signUpUser(String login, String password) {
int[] ret = new int[2];
String request = "SELECT * FROM user WHERE login = '"+login+"' AND password = '" +password+"'";
try {
statement = getDbConnection().createStatement();
resultSet = statement.executeQuery(request);
if(resultSet.next()){
ret[0] =resultSet.getInt("id_user");
ret[1] = resultSet.getInt("role_id (FK)");
}
} catch (SQLException | ClassNotFoundException e) {
throw new RuntimeException(e);
}
return ret;
}
public ResultSet getCoursesInfo(){
String request = "SELECT (SELECT courseName FROM courses WHERE idCourse = `idCourse (FK)`), " +
"grade, idWorker FROM worker";
try {
statement = getDbConnection().createStatement();
resultSet = statement.executeQuery(request);
return resultSet;
} catch (SQLException | ClassNotFoundException e) {
throw new RuntimeException(e);
}
}
@Override
public void start(Stage primaryStage) throws Exception{
Parent root = FXMLLoader.load(getClass().getResource("view/autorization.fxml"));
primaryStage.setTitle("Контроль качества");
primaryStage.getIcons().add(new Image("sample/assets/icon.png"));
primaryStage.setScene(new Scene(root));
primaryStage.show();
}

public static void fill(String querry, TableView<ObservableList<String» table) throws SQLException {
table.getColumns().clear();
ObservableList<ObservableList<String» data = FXCollections.observableArrayList();
ResultSet resultSet = DataBaseHandler.getDataBaseHandler().selectQuery(querry);
for (int i = 0; i < resultSet.getMetaData().getColumnCount(); i++) {
final int j = i;
TableColumn col = new TableColumn(resultSet.getMetaData().getColumnLabel(i + 1));
col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String»() {
public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
return new SimpleStringProperty(param.getValue().get(j).toString());
}
});
table.getColumns().addAll(col);
}
while (resultSet.next()) {
ObservableList<String> row = FXCollections.observableArrayList();
for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
row.add(resultSet.getString(i));
}
data.add(row);
}
table.setItems(data);
}
public class Controller {
@FXML private TextField loginField;
@FXML private PasswordField passwordField;
@FXML private Button signUpBtn;

public void initialize(){

signUpBtn.setOnAction(event->{
String login = loginField.getText().trim();
String password = Main.hashPass(passwordField.getText().trim());
DataBaseHandler dataBaseHandler = DataBaseHandler.getDataBaseHandler();
int[] userInfo = dataBaseHandler.signUpUser(login, password);
if(userInfo[1] == 1){
try {
loginField.getScene().getWindow().hide();
FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/userAccount.fxml"));
Stage stage = new Stage();
stage.setResizable(false);
stage.getIcons().add(new Image("sample/assets/icon.png"));
stage.setScene(new Scene(loader.load()));
UserAccController userController = loader.getController();
userController.initData(userInfo[0]);
stage.show();
} catch (IOException e) {
e.printStackTrace();
}
} else if(userInfo[1]==2) {
try {
loginField.getScene().getWindow().hide();
Parent root = FXMLLoader.load(Main.class.getResource("view/adminAccount.fxml"));
Stage stage = new Stage();
stage.setTitle("Контроль качества");
stage.getIcons().add(new Image("sample/assets/icon.png"));
stage.setScene(new Scene(root));
stage.show();
} catch (IOException e) {
throw new RuntimeException(e);
}
} else{
Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setHeaderText("");
alert.setContentText("Неправильный логин или пароль");
alert.show();
}
});
}
}
