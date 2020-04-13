JFLAGS = -g
JC = javac
JVM= java
LINT = -Xlint:all

.SUFFIXES: .java .class

.java.class:
	$(JC) $(LINT) $*.java
	$(JVM) $(MAIN)
	jar -cvf Project.jar ServerTCP.class

CLASSES = \
	ServerTCP.java

MAIN = ServerTCP

default: classes

classes: $(CLASSES:.java=.class)

run: $(ServerTCP).class
	$(JVM) $(MAIN)