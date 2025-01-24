// src/pages/Signup.tsx
import React, { useState } from "react";
import { Box, TextField, Button, Typography, Link } from "@mui/material";
import { useNavigate } from "react-router-dom";

const Signup: React.FC = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const navigate = useNavigate();

  const handleSignup = () => {
    if (password === confirmPassword) {
      // Call API to create an account (to be implemented)
      navigate("/links");
    } else {
      alert("Passwords do not match!");
    }
  };

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        minHeight: "100vh",
        textAlign: "center",
        px: 2,
      }}
    >
      <Typography variant="h5" gutterBottom>
        Create Your Account
      </Typography>
      <TextField
        label="Email"
        type="email"
        fullWidth
        variant="outlined"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        sx={{ mb: 2, maxWidth: 500 }}
      />
      <TextField
        label="Password"
        type="password"
        fullWidth
        variant="outlined"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        sx={{ mb: 2, maxWidth: 500 }}
      />
      <TextField
        label="Confirm Password"
        type="password"
        fullWidth
        variant="outlined"
        value={confirmPassword}
        onChange={(e) => setConfirmPassword(e.target.value)}
        sx={{ mb: 2, maxWidth: 500 }}
      />
      <Button
        variant="contained"
        color="primary"
        onClick={handleSignup}
        sx={{ mt: 2 }}
      >
        Sign Up
      </Button>
      <Link
        component="button"
        variant="body2"
        onClick={() => navigate("/login")}
        sx={{ mt: 2 }}
      >
        Already have an account? Log in
      </Link>
    </Box>
  );
};

export default Signup;