JC := javac
JFLAGS := -g -Xlint:all
JVM := java

FMT := google-java-format
FMTFLAGS := -i -a

all:
	$(JC) $(JFLAGS) *.java

indent:
	$(FMT) $(FMTFLAGS) *.java

clean:
	$(RM) *.class *~

.PHONY: all indent clean
