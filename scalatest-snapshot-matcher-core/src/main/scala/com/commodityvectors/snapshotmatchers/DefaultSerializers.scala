package com.commodityvectors.snapshotmatchers

import com.commodityvectors.snapshotmatchers.utils.PrettyPrint

trait DefaultSerializers {
  implicit def anySerializer[T]: SnapshotSerializer[T] = (in: T) => PrettyPrint.print(in)
}
