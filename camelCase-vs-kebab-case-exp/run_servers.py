
import subprocess

def run_command_in_new_terminal(command, directory, env_path):
    full_command = f"bash -c 'source {env_path}/bin/activate; cd {directory}; {command}; exec bash'"
    subprocess.Popen(["gnome-terminal", "--", "bash", "-c", full_command])

# Paths and commands
frontend_dir = "./frontend/experiment-02"
backend_dir = "./backend"
env_path = "./backend/.venv"

# Commands
frontend_cmd = "npm run dev"
backend_cmd = "python3 app.py"

# Running the commands
run_command_in_new_terminal(frontend_cmd, frontend_dir, env_path)
run_command_in_new_terminal(backend_cmd, backend_dir, env_path)
