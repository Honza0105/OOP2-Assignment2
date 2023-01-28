package util;

public interface ISubject {
    //method to notify observers of change
    public void notifyObservers(Notification notification);
    public void notifyMainObserver(Notification notification);
}
