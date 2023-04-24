import React, {MouseEvent, useState} from 'react';
import {Avatar, Box, Divider, IconButton, ListItemIcon, Menu, MenuItem, Tooltip} from '@mui/material';
import {Settings, Logout} from '@mui/icons-material';
import {TokenStorageService} from "../../service/auth/TokenStorageService";

const AccountMenu: React.FC = () => {
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
  const open: boolean = Boolean(anchorEl);
  const tokenStorageService = new TokenStorageService();

  const handleClick = (event: MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const signOut = () => {
    setAnchorEl(null);
    tokenStorageService.signOut();
    window.location.reload();
  }

  return (
    <React.Fragment>
      <Box sx={{ display: 'flex', alignItems: 'center' }}>
      <Tooltip title={"Настройки аккаунта"}>
          <IconButton
            onClick={handleClick}
            size="small"
            aria-controls={open ? 'account-menu' : undefined}
            aria-haspopup="true"
            aria-expanded={open ? 'true' : undefined}
          >
            <Avatar sx={{width: 25, height: 25}} />
          </IconButton>
        </Tooltip>
      </Box>
      <Menu
        anchorEl={anchorEl}
        id="account-menu"
        open={open}
        onClose={handleClose}
        onClick={handleClose}
        PaperProps={{
          elevation: 0,
          sx: {
            overflow: 'visible',
            filter: 'drop-shadow(0px 2px 8px rgba(0,0,0,0.32))',
            mt: 1.5,
            '& .MuiAvatar-root': {
              ml: -0.5,
              mr: 1,
            },
            '&:before': {
              content: '""',
              display: 'block',
              position: 'absolute',
              top: 0,
              right: 14,
              width: 10,
              height: 10,
              bgcolor: 'background.paper',
              transform: 'translateY(-50%) rotate(45deg)',
              zIndex: 0,
            },
          },
        }}
        transformOrigin={{ horizontal: 'right', vertical: 'top' }}
        anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
      >
        <MenuItem onClick={handleClose}>
          <ListItemIcon>
            <Avatar sx={{width: 25, height: 25}} />
          </ListItemIcon>
          Мой аккаунт
        </MenuItem>
        <Divider />
        <MenuItem onClick={handleClose}>
          <ListItemIcon>
            <Settings style={{fontSize: "25px"}} />
          </ListItemIcon>
          Настройки
        </MenuItem>
        <MenuItem onClick={signOut}>
          <ListItemIcon>
            <Logout style={{fontSize: "25px"}} />
          </ListItemIcon>
          Выход
        </MenuItem>
      </Menu>
    </React.Fragment>
  );
};

export default AccountMenu;