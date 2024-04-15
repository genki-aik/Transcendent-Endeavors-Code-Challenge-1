import { ChangeEvent, useState } from "react";
import "./App.css";
import api from "./api/axiosConfig";

interface InputNumberResult {
  inputNumber: number;
  inputNumberDoubled: number;
  inputNumberDoubledAndSquared: number;
}

function isNumber(value?: string | number): boolean {
  return value != null && value !== "" && !isNaN(Number(value.toString()));
}

function App() {
  const [inputText, setInputText] = useState("");
  const [error, setError] = useState("");
  const [inputNumberResult, setInputNumberResult] =
    useState<InputNumberResult | null>(null);

  const handlePost = async () => {
    if (!isNumber(inputText)) {
      setError("Input should be a valid number");
      return;
    }

    try {
      const response = await api.post("/api/v1/inputNumberResult/storeNumber", {
        inputNumber: inputText,
      });
      setInputNumberResult(response.data);
    } catch (error) {
      console.error("Error: ", error);
    }
  };

  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    setInputText(event.target.value);
    setError("");
  };

  return (
    <div className="container">
      <h1 className="title">Transcendent Endeavors Code Challenge #1</h1>
      <h2>
        Show the calculation result of your input number doubled and then
        squared!
      </h2>
      <div className="input-container">
        <h2>Input</h2>
        <input
          type="text"
          value={inputText}
          onChange={handleInputChange}
          placeholder="Enter number"
        />
        {error && <p className="error">{error}</p>}
      </div>
      <div className="button-container">
        <button onClick={handlePost}>Calculate!</button>
      </div>
      {inputNumberResult && (
        <div className="response-container">
          <h2>Number Doubled</h2>
          <p>{inputNumberResult.inputNumberDoubled}</p>
          <h2>Number Doubled and Squared</h2>
          <p>{inputNumberResult.inputNumberDoubledAndSquared}</p>
        </div>
      )}
    </div>
  );
}

export default App;
