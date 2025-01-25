import { BrowserRouter, Routes, Route } from "react-router-dom"
import Home from "@/pages/Home"
import Signup from "@/pages/Signup"
import Login from "@/pages/Login"
import Dashboard from "@/pages/Dashboard"

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/login" element={<Login />} />
        <Route path="/dashboard" element={<Dashboard />} />
      </Routes>
    </BrowserRouter>
  )
}
