REM Prepara arquivos
mkdir bin & mkdir bin\lib_external & copy lib_external/sqlite-jdbc-3.27.2.1.jar bin\lib_external

REM Compilacao
javac -sourcepath src -d bin src\mothersday\Main.java

REM Execucao
cd bin & java -cp .;lib_external/sqlite-jdbc-3.27.2.1.jar mothersday/Main & cd ..