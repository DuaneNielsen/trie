package com.github.duanielsen.trie;

import java.util.*;

/**
 * Element of a Trie
 * 
 * @author github.com/duanenielsen
 *
 * @param <T> the Comparable type held by the Trie
 */

public class TrieElement<T extends Comparable<T>> implements Comparable<TrieElement<T>> {

	protected T value;
	protected TrieElement<T> parent;
	protected List<TrieElement<T>> children = new ArrayList<TrieElement<T>>();
	
	public TrieElement(T value, TrieElement<T> parent) {
		super();
		this.value = value;
		this.parent = parent;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public TrieElement<T> getParent() {
		return parent;
	}

	public List<TrieElement<T>> getChildren() {
		return children;
	}
	
	
	/**
	 * Creates and adds a child with the given value 
	 * 
	 * @param the value
	 * @return the child that holds the value
	 */
	public TrieElement<T> addChild(T value) {
		TrieElement<T> child = new TrieElement<T>(value,this);
		children.add(child);
		return child;
	}
	
	/**
	 * Looks for child with given value
	 * 
	 * @param symbol
	 * @return the child element if it exists, null if it does not exist
	 */
	public TrieElement<T> findChild ( T value )  {
		TrieElement<T> matchingChild = null;
		for (TrieElement<T> child: this.getChildren()) {
			if ( child.equals(value)  )  {
				matchingChild = child;
			}
		}
		
		return matchingChild;
	}
	
	/**
	 * returns the path starting from the rootNode to this element (not including the root node of course!)
	 * 
	 * @return a list of TrieElements, starting from the rootNode (but not including it) 
	 * 
	 */
	public List<TrieElement<T>> getPath() {
		List<TrieElement<T>> path = new ArrayList<TrieElement<T>>();
		TrieElement<T> e = this;
		while (e.getParent() != null) {
			path.add(0, e);
			e = e.getParent();
		}
		return path;
	}
	
	/**
	 * returns a list of siblings, (children from the same parent),
	 * that comparable considers "before" this one, does not include self
	 * 
	 * @return
	 */
	public List<TrieElement<T>> getOlderSiblings () {
		if (this.isRoot()) return new ArrayList<TrieElement<T>>();   
		List<TrieElement<T>> siblings = new ArrayList<TrieElement<T>>(this.getParent().getChildren());
		Collections.sort(siblings);
		int index = siblings.indexOf(this);
		siblings.subList(index, siblings.size()).clear();
		return siblings;
	}
	
	/**
	 * Returns a list of the datatype held by the Trie, starting from the rootNode,
	 * to the one held by the element
	 * 
	 * @return
	 */
	
	public List<T> getPathOfValues() {
		List<T> path = new ArrayList<T>();
		TrieElement<T> e = this;
		while (e.getParent() != null) {
			path.add(0,e.getValue());
			e = e.getParent();
		}
		return path;
	}
	
	/**
	 * returns true if this is the root element
	 * 
	 * @return true if this is the root element, false otherwise
	 */
	public boolean isRoot() {
		return (this.parent == null);
	}
	
	public boolean equals(TrieElement<T> o) {
		return (value.compareTo(o.value) == 0);
	}

	public boolean equals(T o) {
		return (value.compareTo(o) == 0);
	}	
	
	public int compareTo(TrieElement<T> o) {
		return value.compareTo(o.value);
	}

	
	
}
