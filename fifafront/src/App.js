import './App.css';
import {BrowserRouter,Route,Routes} from 'react-router-dom';
import Basicpage from './component/Basicpage';
import Record from './record/record';

function App() {
  
  return (
    <BrowserRouter>
      <Routes>
        <Route path = '/' element={<Basicpage />} />
        <Route path = '/record/:nickname' element={<Record />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
