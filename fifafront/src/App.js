import './App.css';
import {BrowserRouter,Route,Routes,redirect} from 'react-router-dom';
import Basicpage from './Basicpage';
import Record from './Record';
function App() {
  
  return (
    <BrowserRouter>
      <Routes>
        <Route path = "/" element={<Basicpage />} />
        <Route path = "/record/:nickname" element={<Record/>} />
        <Route path = "/record" element={<Basicpage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
