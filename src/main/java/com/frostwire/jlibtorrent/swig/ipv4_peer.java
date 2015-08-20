/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.7
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.frostwire.jlibtorrent.swig;

public class ipv4_peer extends torrent_peer {
  private transient long swigCPtr;

  protected ipv4_peer(long cPtr, boolean cMemoryOwn) {
    super(libtorrent_jni.ipv4_peer_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(ipv4_peer obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        libtorrent_jni.delete_ipv4_peer(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public ipv4_peer(tcp_endpoint ip, boolean connectable, int src) {
    this(libtorrent_jni.new_ipv4_peer__SWIG_0(tcp_endpoint.getCPtr(ip), ip, connectable, src), true);
  }

  public ipv4_peer(ipv4_peer p) {
    this(libtorrent_jni.new_ipv4_peer__SWIG_1(ipv4_peer.getCPtr(p), p), true);
  }

  public void setAddr(address_v4 value) {
    libtorrent_jni.ipv4_peer_addr_set(swigCPtr, this, address_v4.getCPtr(value), value);
  }

  public address_v4 getAddr() {
    long cPtr = libtorrent_jni.ipv4_peer_addr_get(swigCPtr, this);
    return (cPtr == 0) ? null : new address_v4(cPtr, false);
  }

}
