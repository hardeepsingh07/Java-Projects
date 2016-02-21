//By Hardeep Singh
import tree.*;

public class MyTreeMap<K extends Comparable<K>,V> {
	private BinaryTree<Element> map;
	java.util.Set<K> keys;  // to return keys in order
	private int size;
		
	public boolean containsKey(K key)
	{
		Element k = search(new Element(key,null), map);
		if(k == null)	return false;
		if(key.compareTo(k.getKey()) == 0)	return true;
		else
			return false;	
	}
	public V put(K key, V value)	
	{
		Element k = new Element(key, value);
		insert(k);
		return value;
	}
	public V get(K key)
	{
		Element k = search(new Element(key,null), map);
		if(k == null)	return null;
		if(key.compareTo(k.getKey()) == 0)	return k.getValue();
		return null;
	}
	public V remove(K key)	
	{
		return get(delete(map,new Element(key, get(key)),null).getKey());
	}
	public int size()	{ 
		return size;	
	}
	public int height()
	{ 
		return height(map);
	}
	public String toString()	
	{ 
		return map.toString();	
	}
	public java.util.Set<K> keySet()
	{
		  keys = new java.util.TreeSet<>();
	      if (map !=null) inOrder(map);
	      return keys;
	}
	private Element search(Element element, BinaryTree<Element> tree)
	{
		if(tree == null)	return null;
		Element root = tree.getRoot();
		if(element.compareTo(root) == 0)	return root;
		if(element.compareTo(root) < 0)		return search(element, tree.getLeft());
		else 
			return search(element, tree.getRight());
	}
	private Element insert(Element element)	
	{
		if(map == null)
		{
			map = new BinaryTree<Element>(element);
			size++;
			return null;
		}
		else 
			return insert(element,map);			
		
	}
	private Element insert(Element element, BinaryTree<Element> tree)	
	{
		Element root = tree.getRoot();
		if(element.compareTo(root) == 0)  {
			root = tree.setRoot(element);
			return root;
		}
		else if(element.compareTo(root) < 0) {
			if(tree.getLeft() == null)	{
				tree.setLeft(new BinaryTree<Element>(element));
				size++;
				return null;
			}
			else	
				return insert(element, tree.getLeft());
		}
		else {
			if(tree.getRight() == null)  {
				tree.setRight(new BinaryTree<Element>(element));
				size++;
				return null;
			}
			else 
				return insert(element, tree.getRight());
		}
	}
	private Element delete(BinaryTree<Element> tree, Element element, BinaryTree<Element> parent)	{
		if(tree == null)	return null;
		Element root = tree.getRoot();
		if(element.compareTo(root) < 0)	return delete(tree.getLeft(), element, parent);
		if(element.compareTo(root) > 0)	return delete(tree.getRight(), element, parent);
		if(element.compareTo(root) == 0)	{
			if(tree.isLeaf())	{
				promote(tree, parent, null);
			} else if(tree.getLeft() == null)	{
				promote(tree, parent, null);
			} else if(tree.getRight() == null)	{
				promote(tree,parent,tree.getLeft());
			}
			else	{
				Element f = tree.setRoot(inOrderSuccessor(tree));
				delete(tree,inOrderSuccessor(tree), parent);
				tree.setRoot(f);
			}
		}
		return root;
	}
	private Element inOrderSuccessor( BinaryTree<Element> tree) {
		if(tree.getRight() == null)	return null;
		else return findtheLMchild(tree.getLeft());
	}
	private Element findtheLMchild(BinaryTree<Element> tree) {
		if(tree == null)	return null;
		else if(tree.getLeft() == null)	return tree.getRoot();
		else return findtheLMchild(tree.getLeft());
	}
	private void promote(BinaryTree<Element> tree, BinaryTree<Element> parent, BinaryTree<Element> newChild)	{
		if(parent == null)	map = newChild;
		else if(tree == parent.getLeft())	parent.setLeft(newChild);
		else parent.setRight(newChild);
	}
	private void inOrder(BinaryTree<Element> tree)	{
		if(tree != null)	{
			inOrder(tree.getLeft());
			keys.add(tree.getRoot().getKey());
			inOrder(tree.getRight());
		}
	}
	private int height(BinaryTree<Element> tree)	{
		if(tree == null)	return 0;
		else return 1 + height(tree.getLeft());		
	}
//------------------------------------------------------------	
	private class Element {
		private K key;
		private V value;
		public Element(K key, V value)	{
			this.key = key;
			this.value = value;
		}
		public K getKey()	{
			return key;
		}
		public V getValue()	{
			return value;
		}
		public int compareTo(Element that)	{
			return this.key.compareTo(that.key);
		}
		public String toString()	{
			return key + "=" + value;
		}
	} // end of class Element
//------------------------------------------------------------------
}// end of BinaryTree

