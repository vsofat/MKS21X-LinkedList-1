class MyLinkedList{
private int length;
private Node start,end;

public MyLinkedList(){
  start= null;
  end= null;
  length=0;
}

public boolean add(int value){
  if(length!=0){//if there is a list
    Node newend = new Node(end,value,null);//setting a new end
    end.setNext(newend);//setting the old end to direct to the new end
    end=newend;
    length++;//increasing the length by one
    return true;
}
  else{//if there is no list
    end=new Node(null,value,null);//create one node as end
    start=end;//node is also the start
    length++;
    return true;
  }
}

public int size(){
  return length;
}
public String toString(){
  if (length == 0){
      return "[]";
    }
  String str = "[";
  Node current = start;
  while (current.next() != null){
      str += current.getData() + ",";
      current = current.next();
    }
    return str + end.getData() + "]";
  }

private Node getNthNode(int N){
  Node current=start;
  for(int i=0;i<N;i++){//loop through list untill you reach N
    current=current.next();
  }
  return current;//return Nth Node
}

public Integer get(int index){
  if(index>length || index<0){//if the index is out of bounds, through exception
    throw new IndexOutOfBoundsException();
  }
  return getNthNode(index).getData();//if not, return the value of the node from get nth node

}
public Integer set(int index, Integer value){
  if(index>=0 && index<=length-1){//if the index is in range
    Integer valuebefore;
    Node spot = getNthNode(index);//old node you are replacing value of
    valuebefore=spot.getData();//value of old node
    spot.setData(value);//set the new value
    return valuebefore;//return old value
}
  else{//if the index is out of bounds, through exception
    throw new IndexOutOfBoundsException();}
}

public boolean contains(Integer value){
  Node current=start;
  for(int i =0;current!=null;i++){//while there is still a next node
    if (current.getData()==value){//if the value of any node is the value you are checking for return true
      return true;
    }
    current=current.next();
  }
  return false;
}

public int indexOf(Integer value){
  Node current=start;
  int indof=-1;//default in case it never triggers
  for(int i =0;current!=null;i++){//looping through all nodes
    if (current.getData()==value){//if its equal, mark value as what node it is
      indof=i;
      }
    current=current.next();
    }
  return indof;//return marked value or -1
  }
public void add(int index, Integer value){
  if(index==length-1){//if index is last element, use old add
    add(value);
  }
  else if(index==0){//if index is zero, trigger add to the beggining
    Node newstart = new Node (null, value, null);//create new beggining
    start.setPrev(newstart);//make the old start the second element
    newstart.setNext(start);
    start=newstart;
    length++;
  }
  else if(index<0 ||index>=length){//if the index is out of bounds, through exception
    throw new IndexOutOfBoundsException();
  }
  else{
    Node newnode = new Node(getNthNode(index-1), value, getNthNode(index));//create new node which comes is inbetween the two
    getNthNode(index).next().setPrev(newnode);//set the index after to have its previous be new one
    getNthNode(index-1).setNext(newnode);//set the one behind it to have new one as next
  }
}
  public Integer remove(int index){
    if(index<0 || index>length){//if the index is out of bounds, through exception
      throw new IndexOutOfBoundsException();
    }
    Node current = getNthNode(index);//creating a temporary current for the node at index
    if(index==length-1){//if removing last
      current.prev().setNext(null);//second to last has no next reference
      end=current.prev();//last is now second to last
    }
    else if(index==0){//if removing first
      current.next().setPrev(null);//making second node have no previous
      start=current.next();//setting start to second
  }
    else{
      Node before= current.prev();//before remove
      Node after = current.next();//after remove
      after.setPrev(before);//after's previous skips current
      before.setNext(after);//before's next skips current
    }
    length--;
    return current.getData();//returning old data
  }

  public boolean remove(Integer value){
    if(!contains(value)){//if value isn't in the linked list, return false
      return false;
    }
    else{
      Node current = start;
      for(int i =0; current!=null;i++){//loop through list
        if(current.getData()==value){//if node's value is equal to the value, remove that index
          remove(i);
        }
        current=current.next();
      }
      return true;
    }
  }
}
