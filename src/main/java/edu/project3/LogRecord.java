package edu.project3;

import java.util.Date;

public record LogRecord(String resource, Date datetime, long size, int status) {
}
