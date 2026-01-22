package com.stock.forecast_service.common.interceptor;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

// Serv
public class CustomHttpRequestWrapper extends HttpServletRequestWrapper {

  private byte[] requestBody;

  public CustomHttpRequestWrapper(HttpServletRequest request) throws IOException {
    super(request);

    InputStream stream = request.getInputStream();

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    byte[] buffer = stream.readAllBytes();

    byteArrayOutputStream.writeBytes(buffer);

    this.requestBody = byteArrayOutputStream.toByteArray();
  }

  public ServletInputStream getInputStream() {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.requestBody);

    return new ServletInputStream() {
      @Override
      public boolean isFinished() {
        return byteArrayInputStream.available() == 0;
      }

      @Override
      public boolean isReady() {
        return true;
      }

      @Override
      public void setReadListener(ReadListener readListener) {
        // Not used.
      }

      @Override
      public int read() throws IOException {
        return byteArrayInputStream.read();
      }
    };
  }

  public byte[] getRequestBody() {
    return this.requestBody;
  }

}
