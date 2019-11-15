import java.util.*;

public class MyBinaryTreeMap<K extends Comparable<? super K>, E>
                    implements MyMap<K, E>, Iterable< MapEntry<K, E> >
{
  private TreeNode<K, E> root;
  private TreeNode<K, E> cursor;
  private int size;
  private int modCounter;

  public MyBinaryTreeMap()
  {
      root = null;
      cursor = null;
      size = 0;
      modCounter = 0;
  }

  public MyBinaryTreeMap(MyMap<K, E> otherMap)
  {
      this();
      Iterator<MapEntry<K, E>> itr = otherMap.iterator();
      while(itr.hasNext())
      {
        MapEntry<K, E> next = itr.next();
        put(next.getKey(), next.getValue());
      }
  }

  public int size()
  { return size; }

  public boolean isEmpty()
  {return (size == 0);}

  private boolean find(K k)
  {
    cursor = root;
    if(cursor == null)
	  return false;

    while(true)
    {
       K key = cursor.key;
       int n = k.compareTo(key);
       if (n == 0)
         return true;
       else if(n < 0)
       {
           if(cursor.left == null)
               return false;
           else cursor = cursor.left;
       }
       else
       {
           if(cursor.right == null)
             return false;
           else cursor = cursor.right;
       }
    }
  }

  public boolean containsKey(K k)
  { return find(k); }

  public E get(K k)
  {
      boolean found = find(k);
      if(!found)
        return null;
      return cursor.value;
  }


  public E put (K k, E value)
  {
    if (isEmpty())
    {
         TreeNode<K, E> temp = new TreeNode<K, E>(k, value, null, null, null);
         root = temp;
         size++;
         modCounter++;
         return null;
    }

    boolean found = find(k);
    if(found)
    {
	  E hold = cursor.value;
	  cursor.value = value;
	  modCounter++;
      return hold;
    }
    TreeNode<K, E> temp = new TreeNode<K, E>(k, value, null, null, cursor);
    K key = cursor.key;
    int n = k.compareTo(key);
    if(n < 0)
    {//insert as left child
         cursor.left = temp;
         modCounter++;
    }
    else
    {//insert as right child
         cursor.right = temp;
         modCounter++;
    }
    size++;
    return null
    ;
  }

  private void extract(TreeNode<K, E> ptr)
  {//remove from the tree the element that ptr is currently pointing at.
    TreeNode<K, E> temp = ptr;
    if(temp.left != null && temp.right != null)
    {//if ptr is pointing at a node with two children.
        ptr = successor(temp);
        temp.key = ptr.key;
        temp.value = ptr.value;
        modCounter++;
    }
    //at this point ptr is pointing at a node with at most one child.
    TreeNode<K, E> replacement = null; 
    if(ptr.left != null)
      replacement = ptr.left;
    else if(ptr.right != null)
      replacement = ptr.right;
    if(replacement != null)
    {//ptr has one child
        replacement.parent = ptr.parent;
        if(ptr.parent == null)
             root = replacement;
        else if(ptr == ptr.parent.left)
             ptr.parent.left = replacement;
        else ptr.parent.right = replacement;
        modCounter++;
        return;
    }
    //replacement is null so ptr has no children
    else if(ptr.parent == null)
        {
    	root = null;
        modCounter++;
        }
    else
    {
        if(ptr == ptr.parent.left)
             ptr.parent.left = null;
        else ptr.parent.right = null;
        modCounter++;
    }
  }

  public boolean remove(K k)
  {
    boolean found = find(k);
    if(found)
    {
        extract(cursor);
        cursor = null;
        size--;
    }
    return found;
  }

  private TreeNode<K, E> successor(TreeNode<K, E> p)
  {
      TreeNode<K, E> temp = null;
      if(p == null)
         return null;
      if(p.right != null)
      {
         temp = p.right;
         while(temp.left != null)
            temp = temp.left;
         return temp;
      }
      temp = p.parent;
      TreeNode<K, E> trail = p;
      while( temp != null && trail == temp.right )
      {
          trail = temp;
          temp = temp.parent;
      }
      return temp;
  }

  private TreeNode<K, E> predecessor(TreeNode<K, E> p)
  {
      TreeNode<K, E> temp = null;
      if(p == null)
         return temp;
      if(p.left != null)
      {
         temp = p.left;
         while(temp.right != null)
            temp = temp.right;
         return temp;
      }
      temp = p.parent;
      TreeNode<K, E> trail = p;
      while( temp != null && trail == temp.left )
      {
          trail = temp;
          temp = temp.parent;
      }
      return temp;
  }


  public void clear()
  {
     root = null;
     cursor = null;
     size = 0;
  }

  public Iterator< MapEntry<K, E> > iterator()
  {
       Iterator< MapEntry<K, E> > itr = new MyTreeItr();
       return itr;
  }

  public Iterator< MapEntry<K, E> > breadthFirstIterator()
  {
      Iterator< MapEntry<K, E> > itr = new MyBreadthFirstItr();
      return itr;
  }
 /*********************************************************************************/
     private class MyBreadthFirstItr implements Iterator<MapEntry<K, E>>
     {
       private TreeNode<K, E> lastReturned;
       private MyQueue<TreeNode<K, E>> q;    

       public MyBreadthFirstItr()
       {
           q = new ListQueue<TreeNode<K, E>>();
           lastReturned = null;
           if(root != null)
             q.offer(root);
       }

       public boolean hasNext()
       { return !q.isEmpty(); }

       public MapEntry<K, E> next()
       {
         if ( q.isEmpty() )
            throw new NoSuchElementException();
         lastReturned = q.front();
         q.poll();
         if (lastReturned.left != null)
             q.offer(lastReturned.left);
         if(lastReturned.right != null)
             q.offer(lastReturned.right);

         K k = lastReturned.key;
         MapEntry<K, E> hold = new MapEntry<K, E>(k, lastReturned.value);
         return hold;
       }

       public void remove()
       { throw new UnsupportedOperationException(); }
     }
 /**********************************************************************************/
     private class MyTreeItr implements Iterator<MapEntry<K, E>>
     {
       private TreeNode<K, E> lastReturned, next;
       private int ItModCounter; 

       public MyTreeItr()
       {
           next = root;
           lastReturned = null;
           ItModCounter = modCounter;
           if(next != null)
             while(next.left != null)
                next = next.left;
       }

       public boolean hasNext()
       { return(next != null);  }

       public MapEntry<K, E> next()
       {
    	 if (modCounter == ItModCounter)
    	 {
	         if (next == null)
	            throw new NoSuchElementException();
	         else
	         {
	           lastReturned = next;
	           next = successor(next);
	           K k = lastReturned.key;
	           MapEntry<K,E> hold = new MapEntry<K,E>(k, lastReturned.value);
	           return hold;
         }
    	 }
    	else
    		throw new ConcurrentModificationException();
       }

       public void remove()
       {
    	   if (modCounter == ItModCounter)
      	 {
	         if (lastReturned == null)
	            throw new IllegalStateException();
	         else
	         {
	             if(lastReturned.right != null && lastReturned.left != null)
	             {//going to replace with successor
	                 next = lastReturned;
	             }
	             ItModCounter++;
	             extract(lastReturned);
	             lastReturned = null;
	             return;
	         }
	       }
    	   else
    		   throw new ConcurrentModificationException();
       }
     }

 /**********************************************************************************
   Inner Class: Implements the nodes used by the Tree.
 **********************************************************************************/

     private static class TreeNode<K, E>
     {
       private K key;
       private E value;
       private TreeNode<K, E> left;
       private TreeNode<K, E> right;
       private TreeNode<K, E> parent;

       public TreeNode()
       {
           key = null;
           value = null;
           left = null;
           right = null;
           parent = null;
       }

       public TreeNode(K k, E o, TreeNode<K, E> lf, TreeNode<K, E> rt)
       {
          key    = k;
          value   = o;
          left   = lf;
          right  = rt;
          parent = null;
       }

       public TreeNode(K k, E o, TreeNode<K, E> lf, TreeNode<K, E> rt, TreeNode<K, E> par)
       {
          key    = k;
          value   = o;
          left   = lf;
          right  = rt;
          parent = par;
       }

       public TreeNode(TreeNode<K, E> otherNode)
       {
         key    = otherNode.key;
         value   = otherNode.value;
         left   = otherNode.left;
         right  = otherNode.right;
         parent = otherNode.parent;
       }
     }
}
