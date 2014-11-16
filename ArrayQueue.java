import java.util.Arrays;

public class ArrayQueue {
  String[] queue;
  int head, tail, length;
  String name;

  public ArrayQueue(int startsize, String n) {
    queue = new String[startsize];
    head = tail = length = 0;
    name = n;
  }

  public void enQ(String s) {
    if(length == queue.length) {
      // could copy to a (2x) larger array
      // or throw an exception
    }

    queue[tail++] = s;
    length++;
    if(tail == queue.length) {
      tail = 0;
    }
    printQueue();
  } 


  public String deQ() throws QueueEmptyException {
    if(isEmpty()){
      throw new QueueEmptyException();
    }

    String s = queue[head];
    queue[head] = null;
    head++;

    if(head == queue.length) {
      head=0;
    }

    length--;
    return s;
  } 

  public String front() throws QueueEmptyException {
    if(isEmpty()) {
      throw new QueueEmptyException();
    }
    return queue[head];
  }

  public int length() { 
    return length; 
  }
 
  public boolean isEmpty() { 
    return length == 0; 
  }

  public void clear() {
    length = 0;
    queue = new String[queue.length];
    head = tail = 0;
  }
 
  public String position(int n) {

    head += n;
    return queue[head];

  }

  private void printQueue() {

    System.out.println(name + " " + Arrays.toString(queue));

  }

} 
