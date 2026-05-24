import { loginUser } from "../services/authService"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

export default function Login() {

    const navigate = useNavigate()

    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")

    const handleLogin = async (e) => {

        e.preventDefault()

        try {

            const data = await loginUser({
                email,
                password
            })

            console.log(data)

            alert("Login Successful")

            localStorage.setItem("token", data.token)
            localStorage.setItem("role", data.role)
            localStorage.setItem("email", data.email)
            localStorage.setItem("name", data.name)

            navigate("/dashboard")

        } catch (error) {

            console.log(error)

            alert("Invalid Credentials")
        }
    }

    return (

        <div className="min-h-screen bg-slate-950 flex justify-center items-center">

            <form
                onSubmit={handleLogin}
                className="bg-slate-900 p-10 rounded-3xl w-[400px] flex flex-col gap-6 shadow-2xl"
            >

                <h1 className="text-white text-4xl font-bold text-center">
                    Login
                </h1>

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

                <button
                    type="submit"
                    className="bg-blue-600 hover:bg-blue-700 transition p-4 rounded-xl text-white font-semibold"
                >
                    Login
                </button>

                <p className="text-gray-400 text-center">

                    Don’t have an account?

                    <span
                        onClick={() => navigate("/register")}
                        className="text-blue-500 cursor-pointer ml-2 hover:text-blue-400"
                    >
                        Register
                    </span>

                </p>

            </form>

        </div>
    )
}