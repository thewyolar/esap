import './sidebar.scss';
import { LineStyle, PermIdentity } from '@mui/icons-material';
import { Link } from 'react-router-dom';
import React from "react";

const Sidebar: React.FC = () => {
  return (
    <div className="sidebar">
      <div className="wrapper">
        <div className="menu">
          <h3>Панель управления</h3>
          <ul>
            <Link to='/' className='link'>
              <li>
                <LineStyle className='icon'/>
                Домашняя
              </li>
            </Link>
          </ul>
        </div>
        <div className="menu">
          <h3>Быстрое меню</h3>
          <ul>
            <Link to='/patients' className='link'>
              <li>
                <PermIdentity className='icon'/>
                Пациенты
              </li>
            </Link>
          </ul>
          <ul>
            <Link to='/shedules' className='link'>
              <li>
                <PermIdentity className='icon'/>
                Расписание врачей
              </li>
            </Link>
          </ul>
        </div>
      </div>
    </div>
  )
}

export default Sidebar;
