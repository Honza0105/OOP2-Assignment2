package util;

public interface IObserver {
    //method to update the observer, used by subject
    public void update(ISubject iSubject, Notification notification);


}
