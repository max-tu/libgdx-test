package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

public class ek<K, V>
{
  private final LinkedHashMap<K, V> pQ;
  private int pR;
  private int pS;
  private int pT;
  private int pU;
  private int pV;
  private int pW;
  private int size;
  
  public ek(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("maxSize <= 0");
    }
    this.pR = paramInt;
    this.pQ = new LinkedHashMap(0, 0.75F, true);
  }
  
  private int c(K paramK, V paramV)
  {
    int i = sizeOf(paramK, paramV);
    if (i < 0) {
      throw new IllegalStateException("Negative size: " + paramK + "=" + paramV);
    }
    return i;
  }
  
  protected V create(K paramK)
  {
    return null;
  }
  
  protected void entryRemoved(boolean paramBoolean, K paramK, V paramV1, V paramV2) {}
  
  public final void evictAll()
  {
    trimToSize(-1);
  }
  
  public final V get(K paramK)
  {
    if (paramK == null) {
      throw new NullPointerException("key == null");
    }
    Object localObject1;
    try
    {
      localObject1 = this.pQ.get(paramK);
      if (localObject1 != null)
      {
        this.pV += 1;
        return (V)localObject1;
      }
      this.pW += 1;
      localObject1 = create(paramK);
      if (localObject1 == null) {
        return null;
      }
    }
    finally {}
    try
    {
      this.pT += 1;
      Object localObject2 = this.pQ.put(paramK, localObject1);
      if (localObject2 != null) {
        this.pQ.put(paramK, localObject2);
      }
      for (;;)
      {
        if (localObject2 == null) {
          break;
        }
        entryRemoved(false, paramK, localObject1, localObject2);
        return (V)localObject2;
        this.size += c(paramK, localObject1);
      }
      trimToSize(this.pR);
    }
    finally {}
    return (V)localObject1;
  }
  
  public final V put(K paramK, V paramV)
  {
    if ((paramK == null) || (paramV == null)) {
      throw new NullPointerException("key == null || value == null");
    }
    try
    {
      this.pS += 1;
      this.size += c(paramK, paramV);
      Object localObject = this.pQ.put(paramK, paramV);
      if (localObject != null) {
        this.size -= c(paramK, localObject);
      }
      if (localObject != null) {
        entryRemoved(false, paramK, localObject, paramV);
      }
      trimToSize(this.pR);
      return (V)localObject;
    }
    finally {}
  }
  
  public final int size()
  {
    try
    {
      int i = this.size;
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected int sizeOf(K paramK, V paramV)
  {
    return 1;
  }
  
  public final String toString()
  {
    int i = 0;
    try
    {
      int j = this.pV + this.pW;
      if (j != 0) {
        i = this.pV * 100 / j;
      }
      String str = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[] { Integer.valueOf(this.pR), Integer.valueOf(this.pV), Integer.valueOf(this.pW), Integer.valueOf(i) });
      return str;
    }
    finally {}
  }
  
  public void trimToSize(int paramInt)
  {
    Object localObject3;
    Object localObject2;
    try
    {
      if ((this.size < 0) || ((this.pQ.isEmpty()) && (this.size != 0))) {
        throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
      }
    }
    finally
    {
      throw ((Throwable)localObject1);
      if ((this.size <= paramInt) || (this.pQ.isEmpty())) {
        return;
      }
      localObject3 = (Map.Entry)this.pQ.entrySet().iterator().next();
      localObject2 = ((Map.Entry)localObject3).getKey();
      localObject3 = ((Map.Entry)localObject3).getValue();
      this.pQ.remove(localObject2);
      this.size -= c(localObject2, localObject3);
      this.pU += 1;
    }
  }
}


/* Location:              G:\libgdx\sample_code\apk\libgdx-tests 0.6.4.3-dex2jar.jar!\com\google\android\gms\internal\ek.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */