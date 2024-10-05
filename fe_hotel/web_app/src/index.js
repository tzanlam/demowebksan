import ReactDOM from 'react-dom/client';
import './index.css';
import {Provider} from 'react-redux';
import { RouterProvider } from 'react-router-dom';
import router from './router/router';
import store from './redux/store';
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  // <React.StrictMode>
  //   <App/>
  // </React.StrictMode>
  <Provider store={store}>
    <RouterProvider router={router}/>
  </Provider>
);
