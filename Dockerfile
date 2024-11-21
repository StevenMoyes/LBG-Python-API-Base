FROM python:latest

WORKDIR /app

COPY . .

RUN pip install -r "requirements.txt"
COPY . .

COPY . .

EXPOSE 8080

ENTRYPOINT ["python", "lbg.py"]
