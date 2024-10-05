import { createSlice } from '@reduxjs/toolkit';
import AccountService from "../../services/AccountService"

const accountSlice = createSlice({
  name: 'account',
  initialState: {
    accounts: [],
    status: 'idle', // trạng thái của các yêu cầu (idle, loading, succeeded, failed)
    error: null,
  },
  reducers: {
    setAccounts: (state, action) => {
      state.accounts = action.payload;
    },
    addAccount: (state, action) => {
      state.accounts.push(action.payload);
    },
    updateAccount: (state, action) => {
      const index = state.accounts.findIndex(account => account.id === action.payload.id);
      if (index !== -1) {
        state.accounts[index] = action.payload;
      }
    },
    deleteAccount: (state, action) => {
      state.accounts = state.accounts.filter(account => account.id !== action.payload);
    },
    setStatus: (state, action) => {
      state.status = action.payload;
    },
    setError: (state, action) => {
      state.error = action.payload;
    },
  },
});

export const {
  setAccounts,
  addAccount,
  updateAccount,
  deleteAccount,
  setStatus,
  setError,
} = accountSlice.actions;

export const fetchAccounts = (page, size) => async (dispatch) => {
  dispatch(setStatus('loading'));
  try {
    const response = await AccountService.getAll(page, size);
    dispatch(setAccounts(response.data));
    dispatch(setStatus('succeeded'));
  } catch (error) {
    dispatch(setError(error.message));
    dispatch(setStatus('failed'));
  }
};

export const createAccount = (account) => async (dispatch) => {
  dispatch(setStatus('loading'));
  try {
    const response = await AccountService.create(account);
    dispatch(addAccount(response.data));
    dispatch(setStatus('succeeded'));
  } catch (error) {
    dispatch(setError(error.message));
    dispatch(setStatus('failed'));
  }
};

export const editAccount = (id, account) => async (dispatch) => {
  dispatch(setStatus('loading'));
  try {
    const response = await AccountService.update(id, account);
    dispatch(updateAccount(response.data));
    dispatch(setStatus('succeeded'));
  } catch (error) {
    dispatch(setError(error.message));
    dispatch(setStatus('failed'));
  }
};

export const removeAccount = (id) => async (dispatch) => {
  dispatch(setStatus('loading'));
  try {
    await AccountService.delete(id);
    dispatch(deleteAccount(id));
    dispatch(setStatus('succeeded'));
  } catch (error) {
    dispatch(setError(error.message));
    dispatch(setStatus('failed'));
  }
};

export default accountSlice.reducer;
