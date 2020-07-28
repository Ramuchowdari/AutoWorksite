echo %CD%
cd %CD%
mvn clean install
java -jar -Dapple.awt.UIElement="true" target/myproject-1.0-SNAPSHOT.jar -h