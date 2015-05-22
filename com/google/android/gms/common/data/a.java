package com.google.android.gms.common.data;

import com.google.android.gms.internal.eg;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class a<T>
  implements Iterator<T>
{
  private final DataBuffer<T> mDataBuffer;
  private int nF;
  
  public a(DataBuffer<T> paramDataBuffer)
  {
    this.mDataBuffer = ((DataBuffer)eg.f(paramDataBuffer));
    this.nF = -1;
  }
  
  public boolean hasNext()
  {
    return this.nF < this.mDataBuffer.getCount() - 1;
  }
  
  public T next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException("Cannot advance the iterator beyond " + this.nF);
    }
    DataBuffer localDataBuffer = this.mDataBuffer;
    int i = this.nF + 1;
    this.nF = i;
    return (T)localDataBuffer.get(i);
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\common\data\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */