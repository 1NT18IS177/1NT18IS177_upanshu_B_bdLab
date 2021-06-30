import React, { useContext } from 'react'
import { GlobalContext } from '../context/GlobalState';

export const BudgetExpenses = () => {
    const { transactions } = useContext(GlobalContext);

    const amounts = transactions.map(transaction => transaction.amount);

    const budget = amounts.filter(item => item > 0).reduce((acc, item) => (acc += item), 0);

    const expenses = Math.abs(amounts.filter(item => item < 0).reduce((acc, item) => (acc += item), 0));

    return (
      <div className="incExpContainer">
            <div>
                <h4>Budget</h4>
                <p className="money plus">₹{budget}</p>
            </div>
            <div>
                <h4>Expenses</h4>
                <p className="money minus">₹{expenses}</p>
            </div>
        </div>
    )
}
