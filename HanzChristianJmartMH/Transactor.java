package HanzChristianJmartMH;

public interface Transactor
{
  public boolean validate();
  public Invoice perform();
}
