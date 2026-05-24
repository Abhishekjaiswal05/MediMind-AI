import { registerUser } from "../services/authService"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

export default function Register() {

    const navigate = useNavigate()

    const [name, setName] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [role, setRole] = useState("PATIENT")

    const handleRegister = async (e) => {

        e.preventDefault()

        try {

            const data = await registerUser({
                name,
                email,
                password,
                role
            })

            alert("Registration Successful")

            navigate("/login")

        } catch (error) {

            console.log(error)

            alert("Registration Failed")
        }
    }

    return (

        <div className="min-h-screen bg-slate-950 flex justify-center items-center">

            <form
                onSubmit={handleRegister}
                className="bg-slate-900 p-10 rounded-3xl w-[400px] flex flex-col gap-6 shadow-2xl"
            >

                <h1 className="text-white text-4xl font-bold text-center">
                    Register
                </h1>

                <input
                    type="text"
                    placeholder="Full Name"
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                    className="p-4 rounded-xl bg-slate-800 text-white outline-none"
                />

                <input
                    type="email"
                    placeholder="Email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    className="p-4 rounded-xl bg-slate-800 text-white outline-none"
                />

                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    className="p-4 rounded-xl bg-slate-800 text-white outline-none"
                />

                <select
                    value={role}
                    onChange={(e) => setRole(e.target.value)}
                    className="w-full p-4 rounded-2xl bg-[#1E293B] text-white mt-4"
                >
                    <option value="PATIENT">Patient</option>
                    <option value="CAREGIVER">Caregiver</option>
                    <option value="ADMIN">Admin</option>
                </select>

                <button
                    type="submit"
                    className="bg-green-600 hover:bg-green-700 transition p-4 rounded-xl text-white font-semibold"
                >
                    Register
                </button>

            </form>

        </div>
    )
}