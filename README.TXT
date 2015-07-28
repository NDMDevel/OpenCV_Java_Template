*************************************************************************
*************************************************************************
*****************************               *****************************
*****************************  README FILE  *****************************
*****************************               *****************************
*************************************************************************
*************************************************************************

1) Resumen:

-> IDE: Netbeans
-> Lenguaje: Java
-> Libreria: OpenCV v2.4.9
-> OS: Linux/Windows (ambas arquitecturas: x86/x64)
-> Tipo de proyecto: Template (implementa un Test de prueba)

2) Descripcion:

Este proyecto es un Template de OpenCV + Java para Netbeans, creado
para ser usado como punto de partida para otros proyectos sin tener
que lidiar con el problema de configurar las dependencias, arquitecturas
(x86/x64) y es independiente del tipo de sistema (Linux/Windows).

El principal logro consiste en que es capaz de generar un paquete
completamente redistribuible capaz de correr en Windows/Linux y en ambas
arquitecturas x86/x64. (Esto se logra incorporando la herramienta OneJAR
que incorpora un makefile -build.xml- capaz de crear los paquetes
redistribuibles.) 

3) Test de prueba:

Este proyecto ademas de ser un Template, incluye un Test que prueba el
correcto funcionamiento y configuracion del proyecto.
El Test se ejecuta cuando el primer argumento de la linea de comando es
"--run-test" (sin comillas). Netbeans por defecto incluye este
argumento en la linea de comando por lo que el Test esta habilitado por
defecto. Para dejar de correr el Test, ir a las propiedades de
proyecto -> Run -> Arguments y borrar el argumento --run-test.

El Test es opcional y pensado para la primer etapa de
desarrollo/depuracion del proyecto las veces de punto de partida, por lo
que se lo puede remover/modificar.

	3.1) Descripcion del Test de prueba:
	El Test, al correr, genera una interface grafica que lee (desde
	el directorio raiz) todas las imagenes con extension .jpg y las
	muestra en pantalla usando OpenCV.

	3.2) Corriendo el Test de prueba desde la linea de comando:
	Al compilar el proyecto, Netbeans genera automaticamente el
	directorio "dist" que contiene el ejecutalbe de java (.jar).
	Para ejecutar la aplicacion de prueba, ejecutar desde la linea de
	comandos lo siguiente:
	java -jar OpenCV_Java_Template-<linux/windows>.jar --run-test

4) Creacion de los paquetes redistribuibles:

Para crear desde Netbeans los ejecutable de java (.jar) para Linux/Windows
de 32 y 64 bits, proceder como siguien:
Desde la solapa "Files", ubicar dentro del directorio del proyecto el
archivo "build.xml", clic derecho -> Run Target -> dist-all.
Esto creara el directorio "dist" con los .jar para linux y windows, ademas
del directorio "natives" que contiene las librerias dinamicas (dll en
windows, so en linux) que deberan instalarse en el sistema operativo en el
que se desea correr la aplicacion.

5) Corriendo los paquetes redistribuibles en otros sistemas:

	5.1) Windows:
	Para correr la aplicacion en Windows, ir a dist\natives\win\x86 o
	dist\natives\win\x64 (segun la arquitectura del sistema), copiar
	la dll que corresponda y pegarla en C:\Windows\System32.
	Luego ejecutar el jar con doble clic o desde la consola con:

	java -jar xxxxx.jar

	5.1) Linux:
	En linux, ir a dist\natives\linux\x86 o dist\natives\linux\x64
	(segun la arquitectura del sistema), copiar la libreria .so
	correspondiente y pegarla en /usr/lib (o /usr/lib32 o /usr/lib64
	o similar).
	Para ejecutar el jar doble clic en el o desde consola tipear:

	java -jar xxxxx.jar
