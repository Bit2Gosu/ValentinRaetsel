public class Value
{
	private int x;
	private int y;
	private boolean isEmpty;

	public static final Value EMPTY = getEmptyValue();

	private Value()
	{
	}

	public Value(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	private static Value getEmptyValue()
	{
		Value value = new Value();
		value.isEmpty = true;
		return value;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Value))
		{
			return false;
		}

		Value other = (Value) obj;
		if (other.isEmpty)
		{
			return isEmpty;
		}
		return other.x == x && other.y == y;
	}

	@Override
	public int hashCode()
	{
		if (isEmpty)
		{
			return -1;
		}
		return x + 100 * y;
	}

	@Override
	public String toString()
	{
		return "(" + x + ";" + y + ")";
	}

	public boolean isEmpty()
	{
		return isEmpty;
	}

	public int getX()
	{
		if (isEmpty)
		{
			throw new RuntimeException("Value is empty");
		}
		return x;
	}

	public int getY()
	{
		if (isEmpty)
		{
			throw new RuntimeException("Value is empty");
		}
		return y;
	}

}
