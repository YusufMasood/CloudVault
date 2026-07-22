export default function Dashboard() {

    return (

        <div className="min-h-screen bg-slate-950 text-white flex flex-col justify-center items-center">

            <h1 className="text-5xl font-bold text-cyan-400">
                Welcome
            </h1>

            <p className="mt-4 text-xl">
                {localStorage.getItem("name")}
            </p>

        </div>

    );

}