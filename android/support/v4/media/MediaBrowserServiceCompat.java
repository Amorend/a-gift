package android.support.v4.media;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.BuildCompat;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class MediaBrowserServiceCompat
  extends Service
{
  static final boolean DEBUG = Log.isLoggable("MBServiceCompat", 3);
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static final String KEY_MEDIA_ITEM = "media_item";
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public static final String KEY_SEARCH_RESULTS = "search_results";
  static final int RESULT_ERROR = -1;
  static final int RESULT_FLAG_ON_LOAD_ITEM_NOT_IMPLEMENTED = 2;
  static final int RESULT_FLAG_ON_SEARCH_NOT_IMPLEMENTED = 4;
  static final int RESULT_FLAG_OPTION_NOT_HANDLED = 1;
  static final int RESULT_OK = 0;
  public static final String SERVICE_INTERFACE = "android.media.browse.MediaBrowserService";
  static final String TAG = "MBServiceCompat";
  final ArrayMap<IBinder, ConnectionRecord> mConnections = new ArrayMap();
  ConnectionRecord mCurConnection;
  final ServiceHandler mHandler = new ServiceHandler();
  private MediaBrowserServiceImpl mImpl;
  MediaSessionCompat.Token mSession;
  
  void addSubscription(String paramString, ConnectionRecord paramConnectionRecord, IBinder paramIBinder, Bundle paramBundle)
  {
    Object localObject = (List)paramConnectionRecord.subscriptions.get(paramString);
    if (localObject == null) {
      localObject = new ArrayList();
    }
    for (;;)
    {
      Iterator localIterator = ((List)localObject).iterator();
      while (localIterator.hasNext())
      {
        Pair localPair = (Pair)localIterator.next();
        if ((paramIBinder == localPair.first) && (MediaBrowserCompatUtils.areSameOptions(paramBundle, (Bundle)localPair.second))) {
          return;
        }
      }
      ((List)localObject).add(new Pair(paramIBinder, paramBundle));
      paramConnectionRecord.subscriptions.put(paramString, localObject);
      performLoadChildren(paramString, paramConnectionRecord, paramBundle);
      return;
    }
  }
  
  List<MediaBrowserCompat.MediaItem> applyOptions(List<MediaBrowserCompat.MediaItem> paramList, Bundle paramBundle)
  {
    if (paramList == null) {
      paramBundle = null;
    }
    int m;
    do
    {
      return paramBundle;
      i = paramBundle.getInt("android.media.browse.extra.PAGE", -1);
      m = paramBundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
      if (i != -1) {
        break;
      }
      paramBundle = paramList;
    } while (m == -1);
    int k = m * i;
    int j = k + m;
    if ((i < 0) || (m < 1) || (k >= paramList.size())) {
      return Collections.EMPTY_LIST;
    }
    int i = j;
    if (j > paramList.size()) {
      i = paramList.size();
    }
    return paramList.subList(k, i);
  }
  
  public void dump(FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  public final Bundle getBrowserRootHints()
  {
    return this.mImpl.getBrowserRootHints();
  }
  
  @Nullable
  public MediaSessionCompat.Token getSessionToken()
  {
    return this.mSession;
  }
  
  boolean isValidPackage(String paramString, int paramInt)
  {
    if (paramString == null) {}
    for (;;)
    {
      return false;
      String[] arrayOfString = getPackageManager().getPackagesForUid(paramInt);
      int i = arrayOfString.length;
      paramInt = 0;
      while (paramInt < i)
      {
        if (arrayOfString[paramInt].equals(paramString)) {
          return true;
        }
        paramInt += 1;
      }
    }
  }
  
  public void notifyChildrenChanged(@NonNull String paramString)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
    }
    this.mImpl.notifyChildrenChanged(paramString, null);
  }
  
  public void notifyChildrenChanged(@NonNull String paramString, @NonNull Bundle paramBundle)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("parentId cannot be null in notifyChildrenChanged");
    }
    if (paramBundle == null) {
      throw new IllegalArgumentException("options cannot be null in notifyChildrenChanged");
    }
    this.mImpl.notifyChildrenChanged(paramString, paramBundle);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return this.mImpl.onBind(paramIntent);
  }
  
  public void onCreate()
  {
    super.onCreate();
    if ((Build.VERSION.SDK_INT >= 26) || (BuildCompat.isAtLeastO())) {
      this.mImpl = new MediaBrowserServiceImplApi24();
    }
    for (;;)
    {
      this.mImpl.onCreate();
      return;
      if (Build.VERSION.SDK_INT >= 23) {
        this.mImpl = new MediaBrowserServiceImplApi23();
      } else if (Build.VERSION.SDK_INT >= 21) {
        this.mImpl = new MediaBrowserServiceImplApi21();
      } else {
        this.mImpl = new MediaBrowserServiceImplBase();
      }
    }
  }
  
  @Nullable
  public abstract BrowserRoot onGetRoot(@NonNull String paramString, int paramInt, @Nullable Bundle paramBundle);
  
  public abstract void onLoadChildren(@NonNull String paramString, @NonNull Result<List<MediaBrowserCompat.MediaItem>> paramResult);
  
  public void onLoadChildren(@NonNull String paramString, @NonNull Result<List<MediaBrowserCompat.MediaItem>> paramResult, @NonNull Bundle paramBundle)
  {
    paramResult.setFlags(1);
    onLoadChildren(paramString, paramResult);
  }
  
  public void onLoadItem(String paramString, @NonNull Result<MediaBrowserCompat.MediaItem> paramResult)
  {
    paramResult.setFlags(2);
    paramResult.sendResult(null);
  }
  
  public void onSearch(@NonNull String paramString, Bundle paramBundle, @NonNull Result<List<MediaBrowserCompat.MediaItem>> paramResult)
  {
    paramResult.setFlags(4);
    paramResult.sendResult(null);
  }
  
  void performLoadChildren(final String paramString, final ConnectionRecord paramConnectionRecord, final Bundle paramBundle)
  {
    Result local1 = new Result(paramString)
    {
      void onResultSent(List<MediaBrowserCompat.MediaItem> paramAnonymousList, int paramAnonymousInt)
      {
        if (MediaBrowserServiceCompat.this.mConnections.get(paramConnectionRecord.callbacks.asBinder()) != paramConnectionRecord)
        {
          if (MediaBrowserServiceCompat.DEBUG) {
            Log.d("MBServiceCompat", "Not sending onLoadChildren result for connection that has been disconnected. pkg=" + paramConnectionRecord.pkg + " id=" + paramString);
          }
          return;
        }
        Object localObject = paramAnonymousList;
        if ((paramAnonymousInt & 0x1) != 0) {
          localObject = MediaBrowserServiceCompat.this.applyOptions(paramAnonymousList, paramBundle);
        }
        try
        {
          paramConnectionRecord.callbacks.onLoadChildren(paramString, (List)localObject, paramBundle);
          return;
        }
        catch (RemoteException paramAnonymousList)
        {
          Log.w("MBServiceCompat", "Calling onLoadChildren() failed for id=" + paramString + " package=" + paramConnectionRecord.pkg);
        }
      }
    };
    this.mCurConnection = paramConnectionRecord;
    if (paramBundle == null) {
      onLoadChildren(paramString, local1);
    }
    for (;;)
    {
      this.mCurConnection = null;
      if (local1.isDone()) {
        break;
      }
      throw new IllegalStateException("onLoadChildren must call detach() or sendResult() before returning for package=" + paramConnectionRecord.pkg + " id=" + paramString);
      onLoadChildren(paramString, local1, paramBundle);
    }
  }
  
  void performLoadItem(String paramString, ConnectionRecord paramConnectionRecord, final ResultReceiver paramResultReceiver)
  {
    paramResultReceiver = new Result(paramString)
    {
      void onResultSent(MediaBrowserCompat.MediaItem paramAnonymousMediaItem, int paramAnonymousInt)
      {
        if ((paramAnonymousInt & 0x2) != 0)
        {
          paramResultReceiver.send(-1, null);
          return;
        }
        Bundle localBundle = new Bundle();
        localBundle.putParcelable("media_item", paramAnonymousMediaItem);
        paramResultReceiver.send(0, localBundle);
      }
    };
    this.mCurConnection = paramConnectionRecord;
    onLoadItem(paramString, paramResultReceiver);
    this.mCurConnection = null;
    if (!paramResultReceiver.isDone()) {
      throw new IllegalStateException("onLoadItem must call detach() or sendResult() before returning for id=" + paramString);
    }
  }
  
  void performSearch(String paramString, Bundle paramBundle, ConnectionRecord paramConnectionRecord, final ResultReceiver paramResultReceiver)
  {
    paramResultReceiver = new Result(paramString)
    {
      void onResultSent(List<MediaBrowserCompat.MediaItem> paramAnonymousList, int paramAnonymousInt)
      {
        if (((paramAnonymousInt & 0x4) != 0) || (paramAnonymousList == null))
        {
          paramResultReceiver.send(-1, null);
          return;
        }
        Bundle localBundle = new Bundle();
        localBundle.putParcelableArray("search_results", (Parcelable[])paramAnonymousList.toArray(new MediaBrowserCompat.MediaItem[0]));
        paramResultReceiver.send(0, localBundle);
      }
    };
    this.mCurConnection = paramConnectionRecord;
    onSearch(paramString, paramBundle, paramResultReceiver);
    this.mCurConnection = null;
    if (!paramResultReceiver.isDone()) {
      throw new IllegalStateException("onSearch must call detach() or sendResult() before returning for query=" + paramString);
    }
  }
  
  boolean removeSubscription(String paramString, ConnectionRecord paramConnectionRecord, IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return paramConnectionRecord.subscriptions.remove(paramString) != null;
    }
    List localList = (List)paramConnectionRecord.subscriptions.get(paramString);
    boolean bool1;
    if (localList != null)
    {
      Iterator localIterator = localList.iterator();
      bool1 = false;
      while (localIterator.hasNext()) {
        if (paramIBinder == ((Pair)localIterator.next()).first)
        {
          localIterator.remove();
          bool1 = true;
        }
      }
      bool2 = bool1;
      if (localList.size() == 0) {
        paramConnectionRecord.subscriptions.remove(paramString);
      }
    }
    for (boolean bool2 = bool1;; bool2 = false) {
      return bool2;
    }
  }
  
  public void setSessionToken(MediaSessionCompat.Token paramToken)
  {
    if (paramToken == null) {
      throw new IllegalArgumentException("Session token may not be null.");
    }
    if (this.mSession != null) {
      throw new IllegalStateException("The session token has already been set.");
    }
    this.mSession = paramToken;
    this.mImpl.setSessionToken(paramToken);
  }
  
  public static final class BrowserRoot
  {
    public static final String EXTRA_OFFLINE = "android.service.media.extra.OFFLINE";
    public static final String EXTRA_RECENT = "android.service.media.extra.RECENT";
    public static final String EXTRA_SUGGESTED = "android.service.media.extra.SUGGESTED";
    @Deprecated
    public static final String EXTRA_SUGGESTION_KEYWORDS = "android.service.media.extra.SUGGESTION_KEYWORDS";
    private final Bundle mExtras;
    private final String mRootId;
    
    public BrowserRoot(@NonNull String paramString, @Nullable Bundle paramBundle)
    {
      if (paramString == null) {
        throw new IllegalArgumentException("The root id in BrowserRoot cannot be null. Use null for BrowserRoot instead.");
      }
      this.mRootId = paramString;
      this.mExtras = paramBundle;
    }
    
    public Bundle getExtras()
    {
      return this.mExtras;
    }
    
    public String getRootId()
    {
      return this.mRootId;
    }
  }
  
  private class ConnectionRecord
  {
    MediaBrowserServiceCompat.ServiceCallbacks callbacks;
    String pkg;
    MediaBrowserServiceCompat.BrowserRoot root;
    Bundle rootHints;
    HashMap<String, List<Pair<IBinder, Bundle>>> subscriptions = new HashMap();
    
    ConnectionRecord() {}
  }
  
  static abstract interface MediaBrowserServiceImpl
  {
    public abstract Bundle getBrowserRootHints();
    
    public abstract void notifyChildrenChanged(String paramString, Bundle paramBundle);
    
    public abstract IBinder onBind(Intent paramIntent);
    
    public abstract void onCreate();
    
    public abstract void setSessionToken(MediaSessionCompat.Token paramToken);
  }
  
  class MediaBrowserServiceImplApi21
    implements MediaBrowserServiceCompat.MediaBrowserServiceImpl, MediaBrowserServiceCompatApi21.ServiceCompatProxy
  {
    Messenger mMessenger;
    Object mServiceObj;
    
    MediaBrowserServiceImplApi21() {}
    
    public Bundle getBrowserRootHints()
    {
      if (this.mMessenger == null) {}
      do
      {
        return null;
        if (MediaBrowserServiceCompat.this.mCurConnection == null) {
          throw new IllegalStateException("This should be called inside of onLoadChildren or onLoadItem methods");
        }
      } while (MediaBrowserServiceCompat.this.mCurConnection.rootHints == null);
      return new Bundle(MediaBrowserServiceCompat.this.mCurConnection.rootHints);
    }
    
    public void notifyChildrenChanged(final String paramString, final Bundle paramBundle)
    {
      if (this.mMessenger == null)
      {
        MediaBrowserServiceCompatApi21.notifyChildrenChanged(this.mServiceObj, paramString);
        return;
      }
      MediaBrowserServiceCompat.this.mHandler.post(new Runnable()
      {
        public void run()
        {
          Iterator localIterator = MediaBrowserServiceCompat.this.mConnections.keySet().iterator();
          while (localIterator.hasNext())
          {
            Object localObject1 = (IBinder)localIterator.next();
            localObject1 = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localObject1);
            Object localObject2 = (List)((MediaBrowserServiceCompat.ConnectionRecord)localObject1).subscriptions.get(paramString);
            if (localObject2 != null)
            {
              localObject2 = ((List)localObject2).iterator();
              while (((Iterator)localObject2).hasNext())
              {
                Pair localPair = (Pair)((Iterator)localObject2).next();
                if (MediaBrowserCompatUtils.hasDuplicatedItems(paramBundle, (Bundle)localPair.second)) {
                  MediaBrowserServiceCompat.this.performLoadChildren(paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject1, (Bundle)localPair.second);
                }
              }
            }
          }
        }
      });
    }
    
    public IBinder onBind(Intent paramIntent)
    {
      return MediaBrowserServiceCompatApi21.onBind(this.mServiceObj, paramIntent);
    }
    
    public void onCreate()
    {
      this.mServiceObj = MediaBrowserServiceCompatApi21.createService(MediaBrowserServiceCompat.this, this);
      MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj);
    }
    
    public MediaBrowserServiceCompatApi21.BrowserRoot onGetRoot(String paramString, int paramInt, Bundle paramBundle)
    {
      Bundle localBundle;
      if ((paramBundle != null) && (paramBundle.getInt("extra_client_version", 0) != 0))
      {
        paramBundle.remove("extra_client_version");
        this.mMessenger = new Messenger(MediaBrowserServiceCompat.this.mHandler);
        localBundle = new Bundle();
        localBundle.putInt("extra_service_version", 1);
        BundleCompat.putBinder(localBundle, "extra_messenger", this.mMessenger.getBinder());
      }
      for (;;)
      {
        paramBundle = MediaBrowserServiceCompat.this.onGetRoot(paramString, paramInt, paramBundle);
        if (paramBundle == null) {
          return null;
        }
        if (localBundle == null) {
          paramString = paramBundle.getExtras();
        }
        for (;;)
        {
          return new MediaBrowserServiceCompatApi21.BrowserRoot(paramBundle.getRootId(), paramString);
          paramString = localBundle;
          if (paramBundle.getExtras() != null)
          {
            localBundle.putAll(paramBundle.getExtras());
            paramString = localBundle;
          }
        }
        localBundle = null;
      }
    }
    
    public void onLoadChildren(String paramString, final MediaBrowserServiceCompatApi21.ResultWrapper<List<Parcel>> paramResultWrapper)
    {
      paramResultWrapper = new MediaBrowserServiceCompat.Result(paramString)
      {
        public void detach()
        {
          paramResultWrapper.detach();
        }
        
        void onResultSent(List<MediaBrowserCompat.MediaItem> paramAnonymousList, int paramAnonymousInt)
        {
          ArrayList localArrayList = null;
          if (paramAnonymousList != null)
          {
            localArrayList = new ArrayList();
            paramAnonymousList = paramAnonymousList.iterator();
            while (paramAnonymousList.hasNext())
            {
              MediaBrowserCompat.MediaItem localMediaItem = (MediaBrowserCompat.MediaItem)paramAnonymousList.next();
              Parcel localParcel = Parcel.obtain();
              localMediaItem.writeToParcel(localParcel, 0);
              localArrayList.add(localParcel);
            }
          }
          paramResultWrapper.sendResult(localArrayList);
        }
      };
      MediaBrowserServiceCompat.this.onLoadChildren(paramString, paramResultWrapper);
    }
    
    public void setSessionToken(MediaSessionCompat.Token paramToken)
    {
      MediaBrowserServiceCompatApi21.setSessionToken(this.mServiceObj, paramToken.getToken());
    }
  }
  
  class MediaBrowserServiceImplApi23
    extends MediaBrowserServiceCompat.MediaBrowserServiceImplApi21
    implements MediaBrowserServiceCompatApi23.ServiceCompatProxy
  {
    MediaBrowserServiceImplApi23()
    {
      super();
    }
    
    public void onCreate()
    {
      this.mServiceObj = MediaBrowserServiceCompatApi23.createService(MediaBrowserServiceCompat.this, this);
      MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj);
    }
    
    public void onLoadItem(String paramString, final MediaBrowserServiceCompatApi21.ResultWrapper<Parcel> paramResultWrapper)
    {
      paramResultWrapper = new MediaBrowserServiceCompat.Result(paramString)
      {
        public void detach()
        {
          paramResultWrapper.detach();
        }
        
        void onResultSent(MediaBrowserCompat.MediaItem paramAnonymousMediaItem, int paramAnonymousInt)
        {
          if (paramAnonymousMediaItem == null)
          {
            paramResultWrapper.sendResult(null);
            return;
          }
          Parcel localParcel = Parcel.obtain();
          paramAnonymousMediaItem.writeToParcel(localParcel, 0);
          paramResultWrapper.sendResult(localParcel);
        }
      };
      MediaBrowserServiceCompat.this.onLoadItem(paramString, paramResultWrapper);
    }
  }
  
  class MediaBrowserServiceImplApi24
    extends MediaBrowserServiceCompat.MediaBrowserServiceImplApi23
    implements MediaBrowserServiceCompatApi24.ServiceCompatProxy
  {
    MediaBrowserServiceImplApi24()
    {
      super();
    }
    
    public Bundle getBrowserRootHints()
    {
      if (MediaBrowserServiceCompat.this.mCurConnection != null)
      {
        if (MediaBrowserServiceCompat.this.mCurConnection.rootHints == null) {
          return null;
        }
        return new Bundle(MediaBrowserServiceCompat.this.mCurConnection.rootHints);
      }
      return MediaBrowserServiceCompatApi24.getBrowserRootHints(this.mServiceObj);
    }
    
    public void notifyChildrenChanged(String paramString, Bundle paramBundle)
    {
      if (paramBundle == null)
      {
        MediaBrowserServiceCompatApi21.notifyChildrenChanged(this.mServiceObj, paramString);
        return;
      }
      MediaBrowserServiceCompatApi24.notifyChildrenChanged(this.mServiceObj, paramString, paramBundle);
    }
    
    public void onCreate()
    {
      this.mServiceObj = MediaBrowserServiceCompatApi24.createService(MediaBrowserServiceCompat.this, this);
      MediaBrowserServiceCompatApi21.onCreate(this.mServiceObj);
    }
    
    public void onLoadChildren(String paramString, final MediaBrowserServiceCompatApi24.ResultWrapper paramResultWrapper, Bundle paramBundle)
    {
      paramResultWrapper = new MediaBrowserServiceCompat.Result(paramString)
      {
        public void detach()
        {
          paramResultWrapper.detach();
        }
        
        void onResultSent(List<MediaBrowserCompat.MediaItem> paramAnonymousList, int paramAnonymousInt)
        {
          ArrayList localArrayList = null;
          if (paramAnonymousList != null)
          {
            localArrayList = new ArrayList();
            paramAnonymousList = paramAnonymousList.iterator();
            while (paramAnonymousList.hasNext())
            {
              MediaBrowserCompat.MediaItem localMediaItem = (MediaBrowserCompat.MediaItem)paramAnonymousList.next();
              Parcel localParcel = Parcel.obtain();
              localMediaItem.writeToParcel(localParcel, 0);
              localArrayList.add(localParcel);
            }
          }
          paramResultWrapper.sendResult(localArrayList, paramAnonymousInt);
        }
      };
      MediaBrowserServiceCompat.this.onLoadChildren(paramString, paramResultWrapper, paramBundle);
    }
  }
  
  class MediaBrowserServiceImplBase
    implements MediaBrowserServiceCompat.MediaBrowserServiceImpl
  {
    private Messenger mMessenger;
    
    MediaBrowserServiceImplBase() {}
    
    public Bundle getBrowserRootHints()
    {
      if (MediaBrowserServiceCompat.this.mCurConnection == null) {
        throw new IllegalStateException("This should be called inside of onLoadChildren or onLoadItem methods");
      }
      if (MediaBrowserServiceCompat.this.mCurConnection.rootHints == null) {
        return null;
      }
      return new Bundle(MediaBrowserServiceCompat.this.mCurConnection.rootHints);
    }
    
    public void notifyChildrenChanged(@NonNull final String paramString, final Bundle paramBundle)
    {
      MediaBrowserServiceCompat.this.mHandler.post(new Runnable()
      {
        public void run()
        {
          Iterator localIterator = MediaBrowserServiceCompat.this.mConnections.keySet().iterator();
          while (localIterator.hasNext())
          {
            Object localObject1 = (IBinder)localIterator.next();
            localObject1 = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localObject1);
            Object localObject2 = (List)((MediaBrowserServiceCompat.ConnectionRecord)localObject1).subscriptions.get(paramString);
            if (localObject2 != null)
            {
              localObject2 = ((List)localObject2).iterator();
              while (((Iterator)localObject2).hasNext())
              {
                Pair localPair = (Pair)((Iterator)localObject2).next();
                if (MediaBrowserCompatUtils.hasDuplicatedItems(paramBundle, (Bundle)localPair.second)) {
                  MediaBrowserServiceCompat.this.performLoadChildren(paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject1, (Bundle)localPair.second);
                }
              }
            }
          }
        }
      });
    }
    
    public IBinder onBind(Intent paramIntent)
    {
      if ("android.media.browse.MediaBrowserService".equals(paramIntent.getAction())) {
        return this.mMessenger.getBinder();
      }
      return null;
    }
    
    public void onCreate()
    {
      this.mMessenger = new Messenger(MediaBrowserServiceCompat.this.mHandler);
    }
    
    public void setSessionToken(final MediaSessionCompat.Token paramToken)
    {
      MediaBrowserServiceCompat.this.mHandler.post(new Runnable()
      {
        public void run()
        {
          Iterator localIterator = MediaBrowserServiceCompat.this.mConnections.values().iterator();
          while (localIterator.hasNext())
          {
            MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = (MediaBrowserServiceCompat.ConnectionRecord)localIterator.next();
            try
            {
              localConnectionRecord.callbacks.onConnect(localConnectionRecord.root.getRootId(), paramToken, localConnectionRecord.root.getExtras());
            }
            catch (RemoteException localRemoteException)
            {
              Log.w("MBServiceCompat", "Connection for " + localConnectionRecord.pkg + " is no longer valid.");
              localIterator.remove();
            }
          }
        }
      });
    }
  }
  
  public static class Result<T>
  {
    private Object mDebug;
    private boolean mDetachCalled;
    private int mFlags;
    private boolean mSendResultCalled;
    
    Result(Object paramObject)
    {
      this.mDebug = paramObject;
    }
    
    public void detach()
    {
      if (this.mDetachCalled) {
        throw new IllegalStateException("detach() called when detach() had already been called for: " + this.mDebug);
      }
      if (this.mSendResultCalled) {
        throw new IllegalStateException("detach() called when sendResult() had already been called for: " + this.mDebug);
      }
      this.mDetachCalled = true;
    }
    
    boolean isDone()
    {
      return (this.mDetachCalled) || (this.mSendResultCalled);
    }
    
    void onResultSent(T paramT, int paramInt) {}
    
    public void sendResult(T paramT)
    {
      if (this.mSendResultCalled) {
        throw new IllegalStateException("sendResult() called twice for: " + this.mDebug);
      }
      this.mSendResultCalled = true;
      onResultSent(paramT, this.mFlags);
    }
    
    void setFlags(int paramInt)
    {
      this.mFlags = paramInt;
    }
  }
  
  private class ServiceBinderImpl
  {
    ServiceBinderImpl() {}
    
    public void addSubscription(final String paramString, final IBinder paramIBinder, final Bundle paramBundle, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          Object localObject = paramServiceCallbacks.asBinder();
          localObject = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localObject);
          if (localObject == null)
          {
            Log.w("MBServiceCompat", "addSubscription for callback that isn't registered id=" + paramString);
            return;
          }
          MediaBrowserServiceCompat.this.addSubscription(paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject, paramIBinder, paramBundle);
        }
      });
    }
    
    public void connect(final String paramString, final int paramInt, final Bundle paramBundle, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      if (!MediaBrowserServiceCompat.this.isValidPackage(paramString, paramInt)) {
        throw new IllegalArgumentException("Package/uid mismatch: uid=" + paramInt + " package=" + paramString);
      }
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          IBinder localIBinder = paramServiceCallbacks.asBinder();
          MediaBrowserServiceCompat.this.mConnections.remove(localIBinder);
          MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = new MediaBrowserServiceCompat.ConnectionRecord(MediaBrowserServiceCompat.this);
          localConnectionRecord.pkg = paramString;
          localConnectionRecord.rootHints = paramBundle;
          localConnectionRecord.callbacks = paramServiceCallbacks;
          localConnectionRecord.root = MediaBrowserServiceCompat.this.onGetRoot(paramString, paramInt, paramBundle);
          if (localConnectionRecord.root == null) {
            Log.i("MBServiceCompat", "No root for client " + paramString + " from service " + getClass().getName());
          }
          for (;;)
          {
            try
            {
              paramServiceCallbacks.onConnectFailed();
              return;
            }
            catch (RemoteException localRemoteException1)
            {
              Log.w("MBServiceCompat", "Calling onConnectFailed() failed. Ignoring. pkg=" + paramString);
              return;
            }
            try
            {
              MediaBrowserServiceCompat.this.mConnections.put(localRemoteException1, localConnectionRecord);
              if (MediaBrowserServiceCompat.this.mSession != null)
              {
                paramServiceCallbacks.onConnect(localConnectionRecord.root.getRootId(), MediaBrowserServiceCompat.this.mSession, localConnectionRecord.root.getExtras());
                return;
              }
            }
            catch (RemoteException localRemoteException2)
            {
              Log.w("MBServiceCompat", "Calling onConnect() failed. Dropping client. pkg=" + paramString);
              MediaBrowserServiceCompat.this.mConnections.remove(localRemoteException1);
            }
          }
        }
      });
    }
    
    public void disconnect(final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          IBinder localIBinder = paramServiceCallbacks.asBinder();
          if ((MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.remove(localIBinder) != null) {}
        }
      });
    }
    
    public void getMediaItem(final String paramString, final ResultReceiver paramResultReceiver, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      if ((TextUtils.isEmpty(paramString)) || (paramResultReceiver == null)) {
        return;
      }
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          Object localObject = paramServiceCallbacks.asBinder();
          localObject = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localObject);
          if (localObject == null)
          {
            Log.w("MBServiceCompat", "getMediaItem for callback that isn't registered id=" + paramString);
            return;
          }
          MediaBrowserServiceCompat.this.performLoadItem(paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject, paramResultReceiver);
        }
      });
    }
    
    public void registerCallbacks(final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks, final Bundle paramBundle)
    {
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          IBinder localIBinder = paramServiceCallbacks.asBinder();
          MediaBrowserServiceCompat.this.mConnections.remove(localIBinder);
          MediaBrowserServiceCompat.ConnectionRecord localConnectionRecord = new MediaBrowserServiceCompat.ConnectionRecord(MediaBrowserServiceCompat.this);
          localConnectionRecord.callbacks = paramServiceCallbacks;
          localConnectionRecord.rootHints = paramBundle;
          MediaBrowserServiceCompat.this.mConnections.put(localIBinder, localConnectionRecord);
        }
      });
    }
    
    public void removeSubscription(final String paramString, final IBinder paramIBinder, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          Object localObject = paramServiceCallbacks.asBinder();
          localObject = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localObject);
          if (localObject == null) {
            Log.w("MBServiceCompat", "removeSubscription for callback that isn't registered id=" + paramString);
          }
          while (MediaBrowserServiceCompat.this.removeSubscription(paramString, (MediaBrowserServiceCompat.ConnectionRecord)localObject, paramIBinder)) {
            return;
          }
          Log.w("MBServiceCompat", "removeSubscription called for " + paramString + " which is not subscribed");
        }
      });
    }
    
    public void search(final String paramString, final Bundle paramBundle, final ResultReceiver paramResultReceiver, final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      if ((TextUtils.isEmpty(paramString)) || (paramResultReceiver == null)) {
        return;
      }
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          Object localObject = paramServiceCallbacks.asBinder();
          localObject = (MediaBrowserServiceCompat.ConnectionRecord)MediaBrowserServiceCompat.this.mConnections.get(localObject);
          if (localObject == null)
          {
            Log.w("MBServiceCompat", "search for callback that isn't registered query=" + paramString);
            return;
          }
          MediaBrowserServiceCompat.this.performSearch(paramString, paramBundle, (MediaBrowserServiceCompat.ConnectionRecord)localObject, paramResultReceiver);
        }
      });
    }
    
    public void unregisterCallbacks(final MediaBrowserServiceCompat.ServiceCallbacks paramServiceCallbacks)
    {
      MediaBrowserServiceCompat.this.mHandler.postOrRun(new Runnable()
      {
        public void run()
        {
          IBinder localIBinder = paramServiceCallbacks.asBinder();
          MediaBrowserServiceCompat.this.mConnections.remove(localIBinder);
        }
      });
    }
  }
  
  private static abstract interface ServiceCallbacks
  {
    public abstract IBinder asBinder();
    
    public abstract void onConnect(String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
      throws RemoteException;
    
    public abstract void onConnectFailed()
      throws RemoteException;
    
    public abstract void onLoadChildren(String paramString, List<MediaBrowserCompat.MediaItem> paramList, Bundle paramBundle)
      throws RemoteException;
  }
  
  private class ServiceCallbacksCompat
    implements MediaBrowserServiceCompat.ServiceCallbacks
  {
    final Messenger mCallbacks;
    
    ServiceCallbacksCompat(Messenger paramMessenger)
    {
      this.mCallbacks = paramMessenger;
    }
    
    private void sendRequest(int paramInt, Bundle paramBundle)
      throws RemoteException
    {
      Message localMessage = Message.obtain();
      localMessage.what = paramInt;
      localMessage.arg1 = 1;
      localMessage.setData(paramBundle);
      this.mCallbacks.send(localMessage);
    }
    
    public IBinder asBinder()
    {
      return this.mCallbacks.getBinder();
    }
    
    public void onConnect(String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
      throws RemoteException
    {
      Bundle localBundle = paramBundle;
      if (paramBundle == null) {
        localBundle = new Bundle();
      }
      localBundle.putInt("extra_service_version", 1);
      paramBundle = new Bundle();
      paramBundle.putString("data_media_item_id", paramString);
      paramBundle.putParcelable("data_media_session_token", paramToken);
      paramBundle.putBundle("data_root_hints", localBundle);
      sendRequest(1, paramBundle);
    }
    
    public void onConnectFailed()
      throws RemoteException
    {
      sendRequest(2, null);
    }
    
    public void onLoadChildren(String paramString, List<MediaBrowserCompat.MediaItem> paramList, Bundle paramBundle)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      localBundle.putBundle("data_options", paramBundle);
      if (paramList != null) {
        if (!(paramList instanceof ArrayList)) {
          break label57;
        }
      }
      label57:
      for (paramString = (ArrayList)paramList;; paramString = new ArrayList(paramList))
      {
        localBundle.putParcelableArrayList("data_media_item_list", paramString);
        sendRequest(3, localBundle);
        return;
      }
    }
  }
  
  private final class ServiceHandler
    extends Handler
  {
    private final MediaBrowserServiceCompat.ServiceBinderImpl mServiceBinderImpl = new MediaBrowserServiceCompat.ServiceBinderImpl(MediaBrowserServiceCompat.this);
    
    ServiceHandler() {}
    
    public void handleMessage(Message paramMessage)
    {
      Bundle localBundle = paramMessage.getData();
      switch (paramMessage.what)
      {
      default: 
        Log.w("MBServiceCompat", "Unhandled message: " + paramMessage + "\n  Service version: " + 1 + "\n  Client version: " + paramMessage.arg1);
        return;
      case 1: 
        this.mServiceBinderImpl.connect(localBundle.getString("data_package_name"), localBundle.getInt("data_calling_uid"), localBundle.getBundle("data_root_hints"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(MediaBrowserServiceCompat.this, paramMessage.replyTo));
        return;
      case 2: 
        this.mServiceBinderImpl.disconnect(new MediaBrowserServiceCompat.ServiceCallbacksCompat(MediaBrowserServiceCompat.this, paramMessage.replyTo));
        return;
      case 3: 
        this.mServiceBinderImpl.addSubscription(localBundle.getString("data_media_item_id"), BundleCompat.getBinder(localBundle, "data_callback_token"), localBundle.getBundle("data_options"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(MediaBrowserServiceCompat.this, paramMessage.replyTo));
        return;
      case 4: 
        this.mServiceBinderImpl.removeSubscription(localBundle.getString("data_media_item_id"), BundleCompat.getBinder(localBundle, "data_callback_token"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(MediaBrowserServiceCompat.this, paramMessage.replyTo));
        return;
      case 5: 
        this.mServiceBinderImpl.getMediaItem(localBundle.getString("data_media_item_id"), (ResultReceiver)localBundle.getParcelable("data_result_receiver"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(MediaBrowserServiceCompat.this, paramMessage.replyTo));
        return;
      case 6: 
        this.mServiceBinderImpl.registerCallbacks(new MediaBrowserServiceCompat.ServiceCallbacksCompat(MediaBrowserServiceCompat.this, paramMessage.replyTo), localBundle.getBundle("data_root_hints"));
        return;
      case 7: 
        this.mServiceBinderImpl.unregisterCallbacks(new MediaBrowserServiceCompat.ServiceCallbacksCompat(MediaBrowserServiceCompat.this, paramMessage.replyTo));
        return;
      }
      this.mServiceBinderImpl.search(localBundle.getString("data_search_query"), localBundle.getBundle("data_search_extras"), (ResultReceiver)localBundle.getParcelable("data_result_receiver"), new MediaBrowserServiceCompat.ServiceCallbacksCompat(MediaBrowserServiceCompat.this, paramMessage.replyTo));
    }
    
    public void postOrRun(Runnable paramRunnable)
    {
      if (Thread.currentThread() == getLooper().getThread())
      {
        paramRunnable.run();
        return;
      }
      post(paramRunnable);
    }
    
    public boolean sendMessageAtTime(Message paramMessage, long paramLong)
    {
      Bundle localBundle = paramMessage.getData();
      localBundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
      localBundle.putInt("data_calling_uid", Binder.getCallingUid());
      return super.sendMessageAtTime(paramMessage, paramLong);
    }
  }
}


/* Location:              C:\Users\12724\Desktop\classes-dex2jar.jar!\android\support\v4\media\MediaBrowserServiceCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */