import './topbar.scss';
import { NotificationsNone, Settings } from '@mui/icons-material/';
import doctorImage from '../../assets/doctor.png';

const Topbar = () => {
    return (
        <div className='topbar'>
            <div className="wrapper">
                <div className="left">
                    <span>ЕСАП</span>
                </div>
                <div className="right">
                    <div className="icon">
                        <NotificationsNone style={{fontSize: "25px"}} />
                        <span style={{fontSize: "12px"}}>2</span>
                    </div>
                    {/*<div className="icon">*/}
                    {/*  <Language />*/}
                    {/*  <span>2</span>*/}
                    {/*</div>*/}
                    <div className="icon">
                        <Settings style={{fontSize: "25px"}} />
                    </div>
                    <div className="icon">
                        <img src={doctorImage} alt="doctor-photo" />
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Topbar
