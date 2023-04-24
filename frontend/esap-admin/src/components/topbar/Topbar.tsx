import './topbar.scss';
import {NotificationsNone} from '@mui/icons-material/';
import React from "react";
import AccountMenu from "../accountMenu/AccountMenu";
import {Link} from "react-router-dom";

const Topbar: React.FC = () => {
  return (
    <div className={'topbar'}>
      <div className={"wrapper"}>
        <div className={"left"}>
          <Link to="/">
            <span>ЕСАП</span>
          </Link>
        </div>
        <div className={"right"}>
          <div className={"icon"}>
            <NotificationsNone style={{fontSize: "25px"}} />
            <span style={{fontSize: "12px"}}>2</span>
          </div>
          <AccountMenu />
        </div>
      </div>
    </div>
  );
};

export default Topbar
