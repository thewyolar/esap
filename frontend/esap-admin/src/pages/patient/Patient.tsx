import { CalendarToday, LocationOn, MailOutline, PermIdentity, PhoneAndroid, Publish } from '@mui/icons-material'
import { Link } from 'react-router-dom';
import './patient.scss'
import React, {useState} from "react";
import {Avatar, Box, Button, Grid, TextField, Typography} from "@mui/material";

// const Patient: React.FC = () => {
//   let { patientId } = useParams();
//   let patientIdInt = parseInt(patientId!);
//   const [patient, setPatient] = useState<Patient>();
//   const [editing, setEditing] = useState(false);
//   const [firstName, setFirstName] = useState();
//   const [lastName, setLastName] = useState();
//   const [age, setAge] = useState();
//   const [email, setEmail] = useState();
//   const [phone, setPhone] = useState();
//   const [address, setAddress] = useState();
//
//   useEffect(() => {
//     HttpService.getPatient(patientIdInt)
//       .then(response => setPatient(response))
//       .catch(error => console.error(error));
//   }, []);
//
//   const handleEdit = () => {
//     setEditing(true);
//   };
//
//   const handleSave = () => {
//     // Update patient data
//     // ...
//
//     setEditing(false);
//   };
//
//   return (
//     <Box sx={{ maxWidth: 600, margin: 'auto', padding: 3 }}>
//       <Typography variant="h4" align="center" gutterBottom>
//         {editing ? 'Edit Patient' : 'Patient Details'}
//       </Typography>
//       <form onSubmit={handleSave}>
//         <TextField
//           label="First Name"
//           fullWidth
//           value={firstName}
//           onChange={(e) => setFirstName(e.target.value)}
//           disabled={!editing}
//           required
//         />
//         <TextField
//           label="Last Name"
//           fullWidth
//           value={lastName}
//           onChange={(e) => setLastName(e.target.value)}
//           disabled={!editing}
//           required
//         />
//         <TextField
//           label="Email"
//           fullWidth
//           value={email}
//           onChange={(e) => setEmail(e.target.value)}
//           disabled={!editing}
//           required
//         />
//         <TextField
//           label="Phone"
//           fullWidth
//           value={phone}
//           onChange={(e) => setPhone(e.target.value)}
//           disabled={!editing}
//           required
//         />
//         <TextField
//           label="Address"
//           fullWidth
//           value={address}
//           onChange={(e) => setAddress(e.target.value)}
//           disabled={!editing}
//           required
//         />
//         {editing ? (
//           <Box sx={{ display: 'flex', justifyContent: 'space-between', mt: 3 }}>
//             <Button variant="contained" color="error" >
//               Cancel
//             </Button>
//             <Button variant="contained" type="submit">
//               Save
//             </Button>
//           </Box>
//         ) : (
//           <Box sx={{ display: 'flex', justifyContent: 'center', mt: 3 }}>
//             <Button variant="contained" onClick={handleEdit}>
//               Edit
//             </Button>
//           </Box>
//         )}
//       </form>
//     </Box>
//   );
// };

const Patient: React.FC = () => {
  const [username, setUsername] = useState("aryastark56");
  const [fullName, setFullName] = useState("Arya Stark");
  const [email, setEmail] = useState("erickkhogarth@gmail.com");
  const [phone, setPhone] = useState("+55 98931-5013");
  const [address, setAddress] = useState("Ceará | Brasil");
  const [avatarUrl, setAvatarUrl] = useState(
    "https://upload.wikimedia.org/wikipedia/pt/f/f2/Arya_stark_s7.jpg"
  );

  const handleUsernameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setUsername(event.target.value);
  };

  const handleFullNameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setFullName(event.target.value);
  };

  const handleEmailChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setEmail(event.target.value);
  };

  const handlePhoneChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPhone(event.target.value);
  };

  const handleAddressChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setAddress(event.target.value);
  };

  const handleAvatarUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader();
      reader.onload = (e) => {
        setAvatarUrl(String(e.target?.result));
      };
      reader.readAsDataURL(event.target.files[0]);
    }
  };

  const handleFormSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    // Handle form submit logic here
  };

  // return (
  //   <div className="userPage">
  //     <div className="titleContainer">
  //       <Typography variant="h1" sx={{flexGrow: 1}}>
  //         Edit User
  //       </Typography>
  //       <Link to="/newPatient">
  //         <Button variant="contained" color="primary">
  //           Create
  //         </Button>
  //       </Link>
  //     </div>
  //     <Box sx={{display: "flex", gap: "20px", marginTop: "20px"}}>
  //       <Avatar sx={{width: "150px", height: "150px"}} src={avatarUrl}/>
  //       <Box sx={{flexGrow: 1}}>
  //         <Typography variant="h5" gutterBottom>
  //           {fullName}
  //         </Typography>
  //         <Typography variant="subtitle1" gutterBottom>
  //           Software Engineer
  //         </Typography>
  //         <Box sx={{display: "flex", flexDirection: "column"}}>
  //           <Typography variant="subtitle2" gutterBottom>
  //             Account Details
  //           </Typography>
  //           <Grid container spacing={1} alignItems="center">
  //             <Grid item>
  //               <PermIdentity/>
  //             </Grid>
  //             <Grid item>
  //               <Typography variant="body1">{username}</Typography>
  //             </Grid>
  //           </Grid>
  //           <Grid container spacing={1} alignItems="center">
  //             <Grid item>
  //               <CalendarToday/>
  //             </Grid>
  //             <Grid item>
  //               <Typography variant="body1">{username}</Typography>
  //             </Grid>
  //           </Grid>
  //         </Box>
  //       </Box>
  //     </Box>
  //   </div>
  // );
  return (
    <div className='userPage'>
      <div className="titleContainer">
        <h1>Edit User</h1>
        <Link to='/newUser'>
          <button>Create</button>
        </Link>
      </div>
      <div className="userContainer">
        <div className="show">
          <div className="top">
            <img src="https://upload.wikimedia.org/wikipedia/pt/f/f2/Arya_stark_s7.jpg" alt="profile" />
            <div className="title">
              <span className="username">Arya Stark</span>
              <span className="userTitle">Software Engineer</span>
            </div>
          </div>
          <div className="bottom">
            <span className="title">Account Details</span>
            <div className="info">
              <PermIdentity className='icon' />
              <span className="infoTitle">aryastark56</span>
            </div>
            <div className="info">
              <CalendarToday className='icon' />
              <span className="infoTitle">15/05/2003</span>
            </div>

            <span className="title">Contact Details</span>

            <div className="info">
              <PhoneAndroid className='icon' />
              <span className="infoTitle">+55 98931-5013</span>
            </div>
            <div className="info">
              <MailOutline className='icon' />
              <span className="infoTitle">erickkhogarth@gmail.com</span>
            </div>
            <div className="info">
              <LocationOn className='icon' />
              <span className="infoTitle">Ceará | Brasil</span>
            </div>
          </div>
        </div>
        <div className="update">
          <span className="title">Edit</span>
          <form>
            <div className="left">
              <div className="updateItem">
                <label>Username</label>
                <input type="text" placeholder='aryastark56'/>
              </div>
              <div className="updateItem">
                <label>Full Name</label>
                <input type="text" placeholder='Arya Stark'/>
              </div>
              <div className="updateItem">
                <label>E-mail</label>
                <input type="text" placeholder='erickkhogarth@gmail.com'/>
              </div>
              <div className="updateItem">
                <label>Phone</label>
                <input type="text" placeholder='+55 98931-5013'/>
              </div>
              <div className="updateItem">
                <label>Address</label>
                <input type="text" placeholder='Ceará | Brasil'/>
              </div>
            </div>
            <div className="right">
              <div className="upload">
                <label htmlFor="file"><img src="https://upload.wikimedia.org/wikipedia/pt/f/f2/Arya_stark_s7.jpg" alt="profilePic"/><Publish className='publish'/></label>
                <input type="file" id="file" style={{display: 'none'}} />
              </div>
              <button>Update</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default Patient;