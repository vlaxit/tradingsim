package setup;

public interface QueueElementFactory<T extends Object> {

	public T createRandomInstance();
	
}
