import React, {useState, useContext} from 'react'
import { GlobalContext } from '../context/GlobalState';

export const AddTransaction = () => {
    const [text, setText] = useState('');
    const [amount, setAmount] = useState(0);

    const { addTransaction } = useContext(GlobalContext);

    const onSubmit = e => {
    e.preventDefault();

    const newTransaction = {
      id: Math.floor(Math.random() * 1000000),
      text,
      amount: +amount
    }

    addTransaction(newTransaction);
  }

    return (
        <>
            <h3>Add new Transaction</h3>
            <form onSubmit={onSubmit}>
                <div className="formControl">
                    <label htmlFor="amount">Amount (-ve: Expenses, +ve: Income)</label>
                    <input type="number" value={amount} onChange={(e) => setAmount(e.target.value)} placeholder="Enter Amount" />
                </div>
                <div className="formControl">
                    <label htmlFor="text">Text</label>
                    <input type="text" value={text} onChange={(e) => setText(e.target.value)} placeholder="Description" />
                </div>
                <button className="btn">Add Transaction</button>
            </form>

        </>
    )
}
