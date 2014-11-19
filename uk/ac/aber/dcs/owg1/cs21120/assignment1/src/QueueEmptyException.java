public class QueueEmptyException extends RuntimeException {
  public QueueEmptyException() {
    super("Attempt to access empty Queue");
  }
} 
