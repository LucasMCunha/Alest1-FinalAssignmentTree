import java.util.LinkedList;

public class GenericTree{

  private class Nodo{
    public Nodo father;
    public String name;
    public int lands;
    public LinkedList<Nodo> subtree;

    public Nodo(String n,int e){
      father=null;
      name="";
      lands=e;
      subtree = new LinkedList<>();
    }

    public void addSubtree(Nodo filho){
      filho.father=this;
      subtree.add(filho);
    }

    public boolean removeSubtree(Nodo filho){
      filho.father=null;
      return subtree.remove(filho);
    }

    public Nodo getSubtree(int index){
      if((subtree.size()==0)||(index<0)||(index>=subtree.size()))
        return null;
      else
        return subtree.get(index);
    }

    public int getSubtreeSize(){
      return subtree.size();
    }

  }

  private Nodo raiz;
  private int count;

  public GenericTree(){
    raiz=null;
    count=0;    
  }

  public Nodo searchNodo(int e, Nodo ref){
    if(ref==null)
      return null;
    
    if(ref.lands==e)
      return ref;
    else  {
      Nodo aux = null;
      for(int i=0; i<ref.getSubtreeSize() && aux==null; i++)
        aux=searchNodo(e, ref.getSubtree(i));
      return aux;
    }
  }

  public boolean add(Integer father,String n,int e){
    Nodo aux = new Nodo(n,e);

    if(father==null){
      setRoot(n,e);
      return true;
    }
    else{
      Nodo ref = searchNodo(father, raiz);
      if(ref==null)
        return false;
      else{
        ref.addSubtree(aux);
        aux.father=ref;
        count++;
        return true;
      }
    }
  }

  public int getRoot(){
    return raiz.lands;
  }

  public void setRoot(String n,int e){
    Nodo aux=new Nodo(n,e);
    if(raiz!=null){
      aux.addSubtree(raiz);
      raiz.father=aux;      
    }
    raiz=aux;
    count++;
  }

  public Integer getParent(int e){
    Nodo aux = searchNodo(e, raiz);
    if((aux==null)||(aux==raiz))
      return null;
    else
      return aux.father.lands;
  }

  public boolean removeBranch(int e){
    return false;
  }

  public boolean contains(int e){
    Nodo aux = searchNodo(e,raiz);
    if(aux==null) 
      return false;
    else
      return true;
  }

  public boolean isInternal(int e){
    Nodo aux = searchNodo(e,raiz);
    if(aux==null)
      return false;

    if(aux.getSubtreeSize()==0)
      return false;
    else
      return true;

  }

  public boolean isExternal(int e){
    Nodo aux = searchNodo(e,raiz);
    if(aux==null)
      return false;

    if(aux.getSubtreeSize()==0)
      return true;
    else
      return false;
  }

  public boolean isRoot(int e){
    if(raiz==null)
      return false;
    else if(raiz.lands!=e)
      return false;
    else 
      return true;
  }

  public boolean isEmpty(){
    return (count==0);
  }

  public int size(){
    return count;
  }

  public void clear(){
    count=0;
    LinkedList<Nodo> eliminar = positionsWidth();
    while(!eliminar.isEmpty()){
      Nodo aux = eliminar.poll();
      aux.father=null;
      aux.subtree.clear();
    }
  }

  public LinkedList<Integer> positionsPre(){
    LinkedList<Integer> listaPreOrder = new LinkedList<>();

    positionsPre(raiz, listaPreOrder);

    return listaPreOrder;
  }

  private void positionsPre(Nodo ref, LinkedList<Integer> lista){
    if(ref!=null){
      lista.add(ref.lands);
      for(int i=0; i<ref.getSubtreeSize(); i++)
        positionsPre(ref.getSubtree(i), lista);
    }
  }
  
  public LinkedList<Nodo> positionsPos(){
    return new LinkedList<>();
  }

  public LinkedList<Nodo> positionsWidth(){

    LinkedList<Nodo> listaEmLargura = new LinkedList<>();
    LinkedList<Nodo> listaDeCaminhamento = new LinkedList<>();

    if(raiz!=null){
      listaDeCaminhamento.add(raiz);
      while(!listaDeCaminhamento.isEmpty()){
        Nodo aux = listaDeCaminhamento.poll();
        listaEmLargura.add(aux);
        for(int i=0; i<aux.getSubtreeSize();i++)
          listaDeCaminhamento.add(aux.getSubtree(i));
      }
    }

    return listaEmLargura;
  }

      ///////////////////////////////////////////
    // Codigos abaixo geram saida para GraphViz
    
    public void geraNodosDOT(Nodo n)
    {
        System.out.println("node [shape = circle];\n");
        
        LinkedList<Nodo> L = new LinkedList<>();
        L = positionsWidth();

        for (int i = 0; i< L.size(); i++ )
        {
            // node1 [label = "1"]
            System.out.println("node" + L.get(i).lands + " [label = \"" +  L.get(i).lands + "\"]") ;
        }
    }

    public void geraConexoesDOT(Nodo n)
    {
        for (int i=0; i<n.getSubtreeSize(); i++)
        {
            Nodo aux = n.getSubtree(i);
            System.out.println("node" + n.lands + " -> " + "node" + aux.lands + ";");
            geraConexoesDOT(aux);
        }
    }
    
    // Gera uma saida no formato DOT
    // Esta saida pode ser visualizada no GraphViz
    // Versoes online do GraphViz pode ser encontradas em
    // http://www.webgraphviz.com/
    // http://viz-js.com/
    // https://dreampuf.github.io/GraphvizOnline 
    public void geraDOT()
    {
        System.out.println("digraph g { \n");
        // node [style=filled];
        
        geraNodosDOT(raiz);
        
        geraConexoesDOT(raiz);
        System.out.println("}\n");
    }  

}