package org.freecode.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TodoList {
	/**
	 * Todolist with due dates as keys for sorting, the values are lists of todo items
	 */
	Map<Date, List<TodoItem>> todoList = new TreeMap<Date, List<TodoItem>>(Collections.reverseOrder());
    
	/**
	 * add an todo item into the todolist
	 * @param anItem
	 */
    public synchronized void addToDoItem( TodoItem anItem )
    {
    	if ( anItem == null || anItem.getDueDate() == null || todoList == null )
    	{
    		throw new RuntimeException("todoList, todoItem, or dueDate of the todoItem cannot be null.");
    	}
    	
    	// if the due date of the give todoitem exists, add the item into the list of corresponding items
    	// otherwise, create a new item list and add the item and the due date into the todo list.
    	if ( todoList.containsKey( anItem.dueDate ) )
    	{
    		todoList.get(anItem.getDueDate()).add(anItem);
    	}
    	else
    	{
    		List<TodoItem> itemList = new ArrayList<TodoItem>();
    		itemList.add(anItem);
    		todoList.put(anItem.getDueDate(),  itemList);
    	}
    }
    
    /**
     * remove the given item from the todo list
     * @param anItem
     */
    public synchronized void removeTodoItem( TodoItem anItem )
    {
    	if ( anItem == null || anItem.getDueDate() == null || todoList == null )
    	{
    		throw new RuntimeException("todoList, todoItem, or dueDate of the todoItem cannot be null.");
    	}
    	
    	if ( todoList.containsKey( anItem.dueDate ) )
    	{
    		List<TodoItem> listItem = todoList.get(anItem.getDueDate());
    		listItem.remove( anItem.getTaskDetail() );
    		if ( listItem.size() < 1 )
    		{
    			todoList.remove( anItem.getDueDate() );
    		}
    	}
    	else
    	{
    		// do nothing is the given due date not exist
    	}
    }
    
    public Set<Date> getDueDates()
    {
    	return todoList.keySet();
    }
    
    /**
     * get all the todo item details by the given due date
     * @param aDate
     * @return
     */
    public List<String> getTodoDetailByDue( Date aDate )
    {
    	List<TodoItem> itemList = null;
    	List<String> todoItemDetailsByDue = null;
    	if ( todoList.containsKey( aDate ) )
    	{
    		itemList = todoList.get( aDate );
    	}
    	
    	if ( itemList != null && itemList.size() > 0 )
    	{
    		todoItemDetailsByDue = new ArrayList<String>();
    		for (Iterator<TodoItem> it = itemList.iterator(); it.hasNext(); )
    		{
    			TodoItem anItem = it.next();
    			todoItemDetailsByDue.add(anItem.getTaskDetail());
    		}
    	}
    	
    	return todoItemDetailsByDue;
    }
    
    /**
     * class of TodoItem
     * @author 
     *
     */
    class TodoItem
	{
		private Date dueDate;
		private String taskDetail;
		
		public Date getDueDate()
		{
			return dueDate;
		}
		
		public void setDueDate(Date aDate)
		{
			dueDate = aDate;
		}
		
		public String getTaskDetail()
		{
			return taskDetail;
		}
		
		public void setTaskDetail( String aDetail )
		{
			taskDetail = aDetail;
		}
	}
}
