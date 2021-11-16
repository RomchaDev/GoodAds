package org.romeo.model.exceptions

import java.io.IOException

class BadRequestException(message: String) : IOException(message)