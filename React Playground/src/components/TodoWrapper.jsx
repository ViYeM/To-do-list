import React, { useState } from 'react';
import { TodoForm } from './TodoForm';
import { TodoList } from './TodoList';
import { v4 as uuidv4 } from "uuid";
export const TodoWrapper = () => {
const [todos, setTodos] = useState([]);
const addTodo = (todo) => {
setTodos([
...todos,
{ id: uuidv4(), description: todo, completed: false },
]);
}
const deleteTodo = (id) => setTodos(todos.filter((todo) => todo.id !== id));
return (
<div className='TodoWrapper'>
<h1>Lista de Tarefas</h1>
<TodoForm addTodo={addTodo}/>
{todos.map((todo) =>
<TodoList
key={todo.id}
task={todo}
deleteTodo={deleteTodo}
/>
)
}
</div>
);
}