package com.frostwire.jlibtorrent;

import com.frostwire.jlibtorrent.swig.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link AddTorrentParams} is a parameter pack for adding torrents to a
 * session. The key fields when adding a torrent are:
 * <ul>
 * <li>ti - when you have a .torrent file</li>
 * <li>url - when you have a magnet link or http URL to the .torrent file</li>
 * <li>info_hash - when all you have is an info-hash (this is similar to a magnet link)</li>
 * </ul>
 * One of those fields need to be set. Another mandatory field is
 * {@link #savePath()}. The {@link AddTorrentParams} object is passed into one of the
 * {@link SessionHandle#addTorrent(AddTorrentParams, ErrorCode)} overloads or
 * {@link SessionHandle#asyncAddTorrent(AddTorrentParams)}.
 * <p/>
 * If you only specify the info-hash, the torrent file will be downloaded
 * from peers, which requires them to support the metadata extension. It also
 * takes an optional {@link #name()} argument. This may be left empty in case no
 * name should be assigned to the torrent. In case it's not, the name is
 * used for the torrent as long as it doesn't have metadata.
 *
 * @author gubatron
 * @author aldenml
 */
public final class AddTorrentParams {

    private final add_torrent_params p;

    public AddTorrentParams(add_torrent_params p) {
        this.p = p;
    }

    public AddTorrentParams() {
        this(add_torrent_params.create_instance());
    }

    public add_torrent_params swig() {
        return p;
    }

    /**
     * Filled in by the constructor. It is used for forward binary compatibility.
     *
     * @return
     */
    public int version() {
        return p.getVersion();
    }

    /**
     * If the torrent doesn't have a tracker, but relies on the DHT to find
     * peers, the ``trackers`` can specify tracker URLs for the torrent.
     *
     * @return
     */
    public ArrayList<String> trackers() {
        string_vector v = p.getTrackers();
        int size = (int) v.size();
        ArrayList<String> l = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            l.add(v.get(i));
        }

        return l;
    }

    /**
     * If the torrent doesn't have a tracker, but relies on the DHT to find
     * peers, the ``trackers`` can specify tracker URLs for the torrent.
     *
     * @param value
     */
    public void trackers(List<String> value) {
        string_vector v = new string_vector();

        for (String s : value) {
            v.push_back(s);
        }

        p.setTrackers(v);
    }

    /**
     * Url seeds to be added to the torrent (`BEP 17`_).
     *
     * @return
     */
    public ArrayList<String> urlSeeds() {
        string_vector v = p.getUrl_seeds();
        int size = (int) v.size();
        ArrayList<String> l = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            l.add(v.get(i));
        }

        return l;
    }

    /**
     * Url seeds to be added to the torrent (`BEP 17`_).
     *
     * @param value
     */
    public void urlSeeds(List<String> value) {
        string_vector v = new string_vector();

        for (String s : value) {
            v.push_back(s);
        }

        p.setUrl_seeds(v);
    }

    /**
     * A list of hostname and port pairs, representing DHT nodes to be added
     * to the session (if DHT is enabled). The hostname may be an IP address.
     *
     * @return
     */
    public ArrayList<Pair<String, Integer>> dhtNodes() {
        string_int_pair_vector v = p.getDht_nodes();
        int size = (int) v.size();
        ArrayList<Pair<String, Integer>> l = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            string_int_pair n = v.get(i);
            l.add(new Pair<>(n.getFirst(), n.getSecond()));
        }

        return l;
    }

    /**
     * A list of hostname and port pairs, representing DHT nodes to be added
     * to the session (if DHT is enabled). The hostname may be an IP address.
     *
     * @param value
     */
    public void dhtNodes(List<Pair<String, Integer>> value) {
        string_int_pair_vector v = new string_int_pair_vector();

        for (Pair<String, Integer> p : value) {
            v.push_back(p.to_string_int_pair());
        }

        p.setDht_nodes(v);
    }

    public String name() {
        return p.getName();
    }

    public void name(String value) {
        p.setName(value);
    }

    /**
     * The path where the torrent is or will be stored. Note that this may
     * also be stored in resume data. If you want the save path saved in
     * the resume data to be used, you need to set the
     * flag_use_resume_save_path flag.
     * <p/>
     * .. note::
     * On windows this path (and other paths) are interpreted as UNC
     * paths. This means they must use backslashes as directory separators
     *
     * @return
     */
    public String savePath() {
        return p.getSave_path();
    }

    /**
     * The path where the torrent is or will be stored. Note that this may
     * also be stored in resume data. If you want the save path saved in
     * the resume data to be used, you need to set the
     * flag_use_resume_save_path flag.
     * <p/>
     * .. note::
     * On windows this path (and other paths) are interpreted as UNC
     * paths. This means they must use backslashes as directory separators
     *
     * @param value
     */
    public void savePath(String value) {
        p.setSave_path(value);
    }

    /*
    public void setResume_data(char_vector value) {
        libtorrent_jni.add_torrent_params_resume_data_set(swigCPtr, this, char_vector.getCPtr(value), value);
    }

    public char_vector getResume_data() {
        long cPtr = libtorrent_jni.add_torrent_params_resume_data_get(swigCPtr, this);
        return (cPtr == 0) ? null : new char_vector(cPtr, false);
    }
    */

    /**
     * @return
     * @see StorageMode
     */
    public StorageMode storageMode() {
        return StorageMode.fromSwig(p.getStorage_mode().swigValue());
    }

    /**
     * @param value
     * @see StorageMode
     */
    public void storageMode(StorageMode value) {
        p.setStorage_mode(storage_mode_t.swigToEnum(value.swig()));
    }

/*
    public void setFile_priorities(unsigned_char_vector value) {
        libtorrent_jni.add_torrent_params_file_priorities_set(swigCPtr, this, unsigned_char_vector.getCPtr(value), value);
    }

    public unsigned_char_vector getFile_priorities() {
        long cPtr = libtorrent_jni.add_torrent_params_file_priorities_get(swigCPtr, this);
        return (cPtr == 0) ? null : new unsigned_char_vector(cPtr, false);
    }
*/

    /**
     * The default tracker id to be used when announcing to trackers. By
     * default this is empty, and no tracker ID is used, since this is an
     * optional argument. If a tracker returns a tracker ID, that ID is used
     * instead of this.
     *
     * @return
     */
    public String trackerId() {
        return p.getTrackerid();
    }

    /**
     * The default tracker id to be used when announcing to trackers. By
     * default this is empty, and no tracker ID is used, since this is an
     * optional argument. If a tracker returns a tracker ID, that ID is used
     * instead of this.
     *
     * @param value
     */
    public void trackerId(String value) {
        p.setTrackerid(value);
    }

    /**
     * If you specify a ``url``, the torrent will be set in
     * ``downloading_metadata`` state until the .torrent file has been
     * downloaded. If there's any error while downloading, the torrent will
     * be stopped and the torrent error state (``torrent_status::error``)
     * will indicate what went wrong. The ``url`` may refer to a magnet link
     * or a regular http URL.
     * <p/>
     * If it refers to an HTTP URL, the info-hash for the added torrent will
     * not be the true info-hash of the .torrent. Instead a placeholder,
     * unique, info-hash is used which is later updated once the .torrent
     * file has been downloaded.
     *
     * @return
     */
    public String url() {
        return p.getUrl();
    }

    /**
     * If you specify a ``url``, the torrent will be set in
     * ``downloading_metadata`` state until the .torrent file has been
     * downloaded. If there's any error while downloading, the torrent will
     * be stopped and the torrent error state (``torrent_status::error``)
     * will indicate what went wrong. The ``url`` may refer to a magnet link
     * or a regular http URL.
     * <p/>
     * If it refers to an HTTP URL, the info-hash for the added torrent will
     * not be the true info-hash of the .torrent. Instead a placeholder,
     * unique, info-hash is used which is later updated once the .torrent
     * file has been downloaded.
     *
     * @param value
     */
    public void url(String value) {
        p.setUrl(value);
    }

    /**
     * Set this to the info hash of the torrent to add in case the info-hash
     * is the only known property of the torrent. i.e. you don't have a
     * .torrent file nor a magnet link.
     *
     * @return
     */
    public Sha1Hash infoHash() {
        return new Sha1Hash(p.getInfo_hash());
    }

    /**
     * Set this to the info hash of the torrent to add in case the info-hash
     * is the only known property of the torrent. i.e. you don't have a
     * .torrent file nor a magnet link.
     *
     * @param value
     */
    public void infoHash(Sha1Hash value) {
        p.setInfo_hash(value.swig());
    }

    /*
        public void setMax_uploads(int value) {
            libtorrent_jni.add_torrent_params_max_uploads_set(swigCPtr, this, value);
        }

        public int getMax_uploads() {
            return libtorrent_jni.add_torrent_params_max_uploads_get(swigCPtr, this);
        }

        public void setMax_connections(int value) {
            libtorrent_jni.add_torrent_params_max_connections_set(swigCPtr, this, value);
        }

        public int getMax_connections() {
            return libtorrent_jni.add_torrent_params_max_connections_get(swigCPtr, this);
        }

        public void setUpload_limit(int value) {
            libtorrent_jni.add_torrent_params_upload_limit_set(swigCPtr, this, value);
        }

        public int getUpload_limit() {
            return libtorrent_jni.add_torrent_params_upload_limit_get(swigCPtr, this);
        }

        public void setDownload_limit(int value) {
            libtorrent_jni.add_torrent_params_download_limit_set(swigCPtr, this, value);
        }

        public int getDownload_limit() {
            return libtorrent_jni.add_torrent_params_download_limit_get(swigCPtr, this);
        }
    */

    /**
     * Flags controlling aspects of this torrent and how it's added. See
     * {@link com.frostwire.jlibtorrent.swig.add_torrent_params.flags_t} for details.
     *
     * @return
     */
    public long flags() {
        return p.get_flags();
    }

    /**
     * Flags controlling aspects of this torrent and how it's added. See
     * {@link com.frostwire.jlibtorrent.swig.add_torrent_params.flags_t} for details.
     *
     * @param flags
     */
    public void flags(long flags) {
        p.set_flags(flags);
    }

    /**
     * {@link TorrentInfo} object with the torrent to add. Unless the url or
     * {@link #infoHash()} is set, this is required to be initialized.
     *
     * @param ti
     */
    public void torrentInfo(TorrentInfo ti) {
        p.set_ti(ti.swig());
    }

    /**
     * Can be set to control the initial file priorities when adding a
     * torrent. The semantics are the same as for
     * {@link TorrentHandle#prioritizeFiles(Priority[])}.
     *
     * @param priorities
     */
    public void filePriorities(Priority[] priorities) {
        p.setFile_priorities(Priority.array2byte_vector(priorities));
    }

    /**
     * This sets the priorities for each individual piece in the torrent. Each
     * element in the vector represent the piece with the same index. If you
     * set both file- and piece priorities, file priorities will take
     * precedence.
     *
     * @param priorities
     */
    public void piecePriorities(Priority[] priorities) {
        p.setPiece_priorities(Priority.array2byte_vector(priorities));
    }

    public static AddTorrentParams createInstance() {
        return new AddTorrentParams(add_torrent_params.create_instance());
    }

    public static AddTorrentParams createInstanceDisabledStorage() {
        return new AddTorrentParams(add_torrent_params.create_instance_disabled_storage());
    }

    public static AddTorrentParams createInstanceZeroStorage() {
        return new AddTorrentParams(add_torrent_params.create_instance_zero_storage());
    }
}
