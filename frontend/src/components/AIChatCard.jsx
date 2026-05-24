import { motion } from "framer-motion"
import { useState } from "react"
export default function AIChatCard() {

    const [message, setMessage] = useState("")

    const [messages, setMessages] = useState([
        {
            id: 1,
            sender: "ai",
            text: "Hello 👋 I analyzed today's medication schedule. Don't forget your blood pressure tablet after breakfast."
        }
    ])

    const handleSendMessage = () => {

        if (!message.trim()) return

        const newMessage = {
            id: Date.now(),
            sender: "user",
            text: message
        }

        setMessages((prev) => [...prev, newMessage])

        setMessage("")
    }

    return (
        <div className="bg-[#27345F] rounded-3xl p-8 shadow-lg mt-10">

            {/* Header */}
            <div className="flex items-center gap-5">

                <motion.div
                    animate={{
                        scale: [1, 1.1, 1]
                    }}
                    transition={{
                        duration: 2,
                        repeat: Number.POSITIVE_INFINITY
                    }}
                    className="w-16 h-16 rounded-full bg-cyan-400 shadow-[0_0_40px_#22d3ee]"
                ></motion.div>
                <div>

                    <h2 className="text-4xl font-bold text-white">
                        MediMind AI Assistant
                    </h2>

                    <p className="text-gray-300 mt-1">
                        Online • Ready to help
                    </p>

                </div>

            </div>

            {/* Messages */}
            <div className="mt-8 flex flex-col gap-4">

                {
                    messages.map((msg) => (

                        <div
                            key={msg.id}
                            className={`
                max-w-[80%]
                px-6
                py-4
                rounded-2xl
                text-white
                text-lg
                ${msg.sender === "ai"
                                    ? "bg-[#1F5F8B]"
                                    : "bg-blue-600 self-end"
                                }
              `}
                        >

                            {msg.text}

                        </div>

                    ))
                }

            </div>

            {/* Input */}
            <div className="flex gap-4 mt-8">

                <input
                    type="text"
                    placeholder="Write a message..."
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                    className="
            flex-1
            px-6
            py-4
            rounded-2xl
            bg-[#1B2448]
            text-white
            outline-none
            border border-gray-600
          "
                />

                <motion.button
                    whileHover={{
                        scale: 1.08,
                        boxShadow: "0px 0px 20px rgba(34,211,238,0.8)"
                    }}
                    whileTap={{
                        scale: 0.95
                    }}
                    onClick={handleSendMessage}
                    className="
            bg-cyan-500
            hover:bg-cyan-600
            transition
            text-white
            px-8
            rounded-2xl
            text-lg
            font-semibold
          "
                >
                    Send
                </motion.button>

            </div>

        </div>
    )
}