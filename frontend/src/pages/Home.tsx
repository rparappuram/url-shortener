// src/pages/Home.tsx
import React, { useState } from "react";
import { Box, TextField, Button, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { shortenURL } from "../services/api";

const Home: React.FC = () => {
  const [longURL, setLongURL] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async () => {
    if (longURL.trim()) {
      try {
        const data = await shortenURL(longURL);
        console.log(data); // Debug the response
        navigate("/signup");
      } catch (error) {
        console.error("Error shortening URL:", error);
      }
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
      <Typography variant="h4" gutterBottom>
        Shorten a Long Link
      </Typography>
      <Typography variant="subtitle1" gutterBottom>
        No credit card required
      </Typography>
      <TextField
        label="Paste your long link here"
        placeholder="https://example.com/your-long-url"
        fullWidth
        variant="outlined"
        value={longURL}
        onChange={(e) => setLongURL(e.target.value)}
        sx={{ mb: 2, maxWidth: 500 }}
      />
      <Button
        variant="contained"
        color="primary"
        onClick={handleSubmit}
        sx={{ mt: 2 }}
      >
        Get Your Link for Free
      </Button>
    </Box>
  );
};

export default Home;