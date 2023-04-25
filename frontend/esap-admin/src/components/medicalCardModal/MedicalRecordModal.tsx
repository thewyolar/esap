import { Modal, Box, Grid, Typography, Pagination } from "@mui/material";
import React, { useState } from "react";
import { MedicalRecord } from "../../model/MedicalRecord";

interface MedicalRecordModalProps {
  open: boolean;
  onClose: () => void;
  medicalRecord: MedicalRecord;
}


const MedicalRecordModal: React.FC<MedicalRecordModalProps> = ({open, onClose, medicalRecord}) => {

  return (
    <Modal
      open={open}
      onClose={onClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box
        sx={{
          margin: "auto",
          padding: 3,
          width: "70%",
          height: "90%",
          bgcolor: "background.paper",
          borderRadius: "5px",
          overflow: "auto",
          marginTop: "30px",
        }}
      >
        <Typography id="modal-modal-title" variant="h6" component="h2">
        {<div> {medicalRecord.date} </div>}
        {<div> {medicalRecord.fioAndSpecializationDoctor} </div>}
        {<div> {medicalRecord.record} </div>}
        </Typography>
        <Typography id="modal-modal-description" sx={{ mt: 2 }}>
          Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
        </Typography>
      </Box>
    </Modal>
  );
};

export default MedicalRecordModal;
