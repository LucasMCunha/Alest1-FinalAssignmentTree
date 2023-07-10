import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

private void crianodo(String texto){
int linecont=0;
String[] line=texto.split(";");
String[] v=line[linecont].split(" ");
String f= v[0];
String n= v[1];
String l= v[2];
if(f=="null"){
    Node cre = new Node(null,n,l);
}
else {
    Node cre = new Node(f,n,l);
}
}  
public void Texto(){
    crianodo("Tribo.txt");
}
}
