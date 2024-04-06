package ru.lavafrai.maiapp.lavamarkup.renderer.tags

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.Accusoft
import compose.icons.lineawesomeicons.AdSolid
import compose.icons.lineawesomeicons.AddressCardSolid
import compose.icons.lineawesomeicons.Affiliatetheme
import compose.icons.lineawesomeicons.AirFreshenerSolid
import compose.icons.lineawesomeicons.Airbnb
import compose.icons.lineawesomeicons.AlignCenterSolid
import compose.icons.lineawesomeicons.Amazon
import compose.icons.lineawesomeicons.Amilia
import compose.icons.lineawesomeicons.AnchorSolid
import compose.icons.lineawesomeicons.AngleDoubleLeftSolid
import compose.icons.lineawesomeicons.AngleDoubleRightSolid
import compose.icons.lineawesomeicons.AngleDoubleUpSolid
import compose.icons.lineawesomeicons.AngleRightSolid
import compose.icons.lineawesomeicons.Angry
import compose.icons.lineawesomeicons.AppleAltSolid
import compose.icons.lineawesomeicons.ArrowAltCircleRightSolid
import compose.icons.lineawesomeicons.ArrowAltCircleUp
import compose.icons.lineawesomeicons.ArrowAltCircleUpSolid
import compose.icons.lineawesomeicons.ArrowDownSolid
import compose.icons.lineawesomeicons.ArrowLeftSolid
import compose.icons.lineawesomeicons.ArrowUpSolid
import compose.icons.lineawesomeicons.ArrowsAltVSolid
import compose.icons.lineawesomeicons.Artstation
import compose.icons.lineawesomeicons.AssistiveListeningSystemsSolid
import compose.icons.lineawesomeicons.AsteriskSolid
import compose.icons.lineawesomeicons.Asymmetrik
import compose.icons.lineawesomeicons.AtlasSolid
import compose.icons.lineawesomeicons.Atlassian
import compose.icons.lineawesomeicons.AtomSolid
import compose.icons.lineawesomeicons.AudioDescriptionSolid
import compose.icons.lineawesomeicons.BaconSolid
import compose.icons.lineawesomeicons.BalanceScaleSolid
import compose.icons.lineawesomeicons.BandAidSolid
import compose.icons.lineawesomeicons.BaseballBallSolid
import compose.icons.lineawesomeicons.BasketballBallSolid
import compose.icons.lineawesomeicons.BatteryEmptySolid
import compose.icons.lineawesomeicons.BatteryQuarterSolid
import compose.icons.lineawesomeicons.BatteryThreeQuartersSolid
import compose.icons.lineawesomeicons.BattleNet
import compose.icons.lineawesomeicons.BehanceSquare
import compose.icons.lineawesomeicons.BellSlash
import compose.icons.lineawesomeicons.BezierCurveSolid
import compose.icons.lineawesomeicons.BibleSolid
import compose.icons.lineawesomeicons.BikingSolid
import compose.icons.lineawesomeicons.BinocularsSolid
import compose.icons.lineawesomeicons.BiohazardSolid
import compose.icons.lineawesomeicons.Bitcoin
import compose.icons.lineawesomeicons.Blackberry
import compose.icons.lineawesomeicons.BlindSolid
import compose.icons.lineawesomeicons.BlogSolid
import compose.icons.lineawesomeicons.BoldSolid
import compose.icons.lineawesomeicons.BookDeadSolid
import compose.icons.lineawesomeicons.BookOpenSolid
import compose.icons.lineawesomeicons.Bootstrap
import compose.icons.lineawesomeicons.BorderAllSolid
import compose.icons.lineawesomeicons.BorderStyleSolid
import compose.icons.lineawesomeicons.BrainSolid
import compose.icons.lineawesomeicons.BriefcaseSolid
import compose.icons.lineawesomeicons.BrushSolid
import compose.icons.lineawesomeicons.Btc
import compose.icons.lineawesomeicons.Buffer
import compose.icons.lineawesomeicons.BugSolid
import compose.icons.lineawesomeicons.BullseyeSolid
import compose.icons.lineawesomeicons.BurnSolid
import compose.icons.lineawesomeicons.BusAltSolid
import compose.icons.lineawesomeicons.BusSolid
import compose.icons.lineawesomeicons.BuyNLarge
import compose.icons.lineawesomeicons.CalendarCheck
import compose.icons.lineawesomeicons.CalendarCheckSolid
import compose.icons.lineawesomeicons.CalendarPlusSolid
import compose.icons.lineawesomeicons.CalendarSolid
import compose.icons.lineawesomeicons.CameraSolid
import compose.icons.lineawesomeicons.CampgroundSolid
import compose.icons.lineawesomeicons.CannabisSolid
import compose.icons.lineawesomeicons.CapsulesSolid
import compose.icons.lineawesomeicons.CarBatterySolid
import compose.icons.lineawesomeicons.CarCrashSolid
import compose.icons.lineawesomeicons.CarSolid
import compose.icons.lineawesomeicons.CaretLeftSolid
import compose.icons.lineawesomeicons.CaretSquareDownSolid
import compose.icons.lineawesomeicons.CaretSquareLeft
import compose.icons.lineawesomeicons.CaretSquareLeftSolid
import compose.icons.lineawesomeicons.CaretSquareUp
import compose.icons.lineawesomeicons.CaretUpSolid
import compose.icons.lineawesomeicons.CarrotSolid
import compose.icons.lineawesomeicons.CartPlusSolid
import compose.icons.lineawesomeicons.CashRegisterSolid
import compose.icons.lineawesomeicons.CcAmex
import compose.icons.lineawesomeicons.CcDiscover
import compose.icons.lineawesomeicons.CcJcb
import compose.icons.lineawesomeicons.CcMastercard
import compose.icons.lineawesomeicons.CcPaypal
import compose.icons.lineawesomeicons.ChairSolid
import compose.icons.lineawesomeicons.ChalkboardSolid
import compose.icons.lineawesomeicons.ChalkboardTeacherSolid
import compose.icons.lineawesomeicons.ChargingStationSolid
import compose.icons.lineawesomeicons.ChartAreaSolid
import compose.icons.lineawesomeicons.ChartBar
import compose.icons.lineawesomeicons.ChartBarSolid
import compose.icons.lineawesomeicons.ChartPieSolid
import compose.icons.lineawesomeicons.CheckCircleSolid
import compose.icons.lineawesomeicons.CheckDoubleSolid
import compose.icons.lineawesomeicons.CheckSolid
import compose.icons.lineawesomeicons.CheckSquare
import compose.icons.lineawesomeicons.ChessBoardSolid
import compose.icons.lineawesomeicons.ChessRookSolid
import compose.icons.lineawesomeicons.ChessSolid
import compose.icons.lineawesomeicons.ChevronLeftSolid
import compose.icons.lineawesomeicons.Circle
import compose.icons.lineawesomeicons.CitySolid
import compose.icons.lineawesomeicons.ClipboardCheckSolid
import compose.icons.lineawesomeicons.Clock
import compose.icons.lineawesomeicons.Clone
import compose.icons.lineawesomeicons.ClosedCaptioningSolid
import compose.icons.lineawesomeicons.CloudMoonRainSolid
import compose.icons.lineawesomeicons.CloudMoonSolid
import compose.icons.lineawesomeicons.CloudShowersHeavySolid
import compose.icons.lineawesomeicons.CloudSolid
import compose.icons.lineawesomeicons.CloudSunRainSolid
import compose.icons.lineawesomeicons.CloudSunSolid
import compose.icons.lineawesomeicons.Cloudsmith
import compose.icons.lineawesomeicons.Codepen
import compose.icons.lineawesomeicons.CogsSolid
import compose.icons.lineawesomeicons.CoinsSolid
import compose.icons.lineawesomeicons.CommentAlt
import compose.icons.lineawesomeicons.CommentDots
import compose.icons.lineawesomeicons.CommentSolid
import compose.icons.lineawesomeicons.Comments
import compose.icons.lineawesomeicons.CookieBiteSolid
import compose.icons.lineawesomeicons.CreativeCommonsNd
import compose.icons.lineawesomeicons.CreativeCommonsPd
import compose.icons.lineawesomeicons.CriticalRole
import compose.icons.lineawesomeicons.CrossSolid
import compose.icons.lineawesomeicons.CrosshairsSolid
import compose.icons.lineawesomeicons.Css3
import compose.icons.lineawesomeicons.CubeSolid
import compose.icons.lineawesomeicons.CubesSolid
import compose.icons.lineawesomeicons.CutSolid
import compose.icons.lineawesomeicons.DAndD
import compose.icons.lineawesomeicons.DeafSolid
import compose.icons.lineawesomeicons.DemocratSolid
import compose.icons.lineawesomeicons.Deskpro
import compose.icons.lineawesomeicons.Dev
import compose.icons.lineawesomeicons.Deviantart
import compose.icons.lineawesomeicons.DiceSixSolid
import compose.icons.lineawesomeicons.DiceThreeSolid
import compose.icons.lineawesomeicons.Digg
import compose.icons.lineawesomeicons.DigitalTachographSolid
import compose.icons.lineawesomeicons.Discord
import compose.icons.lineawesomeicons.Dizzy
import compose.icons.lineawesomeicons.DizzySolid
import compose.icons.lineawesomeicons.DnaSolid
import compose.icons.lineawesomeicons.Dochub
import compose.icons.lineawesomeicons.DogSolid
import compose.icons.lineawesomeicons.DollyFlatbedSolid
import compose.icons.lineawesomeicons.DoorClosedSolid
import compose.icons.lineawesomeicons.DownloadSolid
import compose.icons.lineawesomeicons.DraftingCompassSolid
import compose.icons.lineawesomeicons.DribbbleSquare
import compose.icons.lineawesomeicons.Dropbox
import compose.icons.lineawesomeicons.DrumSolid
import compose.icons.lineawesomeicons.DrumSteelpanSolid
import compose.icons.lineawesomeicons.DumpsterSolid
import compose.icons.lineawesomeicons.DungeonSolid
import compose.icons.lineawesomeicons.Dyalog
import compose.icons.lineawesomeicons.Earlybirds
import compose.icons.lineawesomeicons.Ebay
import compose.icons.lineawesomeicons.Edge
import compose.icons.lineawesomeicons.Edit
import compose.icons.lineawesomeicons.EllipsisHSolid
import compose.icons.lineawesomeicons.EllipsisVSolid
import compose.icons.lineawesomeicons.Ember
import compose.icons.lineawesomeicons.Envelope
import compose.icons.lineawesomeicons.EnvelopeOpenTextSolid
import compose.icons.lineawesomeicons.EnvelopeSolid
import compose.icons.lineawesomeicons.EnvelopeSquareSolid
import compose.icons.lineawesomeicons.Envira
import compose.icons.lineawesomeicons.EqualsSolid
import compose.icons.lineawesomeicons.EraserSolid
import compose.icons.lineawesomeicons.Etsy
import compose.icons.lineawesomeicons.Evernote
import compose.icons.lineawesomeicons.ExchangeAltSolid
import compose.icons.lineawesomeicons.ExclamationCircleSolid
import compose.icons.lineawesomeicons.ExclamationTriangleSolid
import compose.icons.lineawesomeicons.ExpandSolid
import compose.icons.lineawesomeicons.Expeditedssl
import compose.icons.lineawesomeicons.ExternalLinkAltSolid
import compose.icons.lineawesomeicons.EyeSlashSolid
import compose.icons.lineawesomeicons.FacebookF
import compose.icons.lineawesomeicons.FacebookMessenger
import compose.icons.lineawesomeicons.FacebookSquare
import compose.icons.lineawesomeicons.FanSolid
import compose.icons.lineawesomeicons.FastBackwardSolid
import compose.icons.lineawesomeicons.FastForwardSolid
import compose.icons.lineawesomeicons.FaxSolid
import compose.icons.lineawesomeicons.FeatherSolid
import compose.icons.lineawesomeicons.FemaleSolid
import compose.icons.lineawesomeicons.FileAlt
import compose.icons.lineawesomeicons.FileAltSolid
import compose.icons.lineawesomeicons.FileArchive
import compose.icons.lineawesomeicons.FileAudioSolid
import compose.icons.lineawesomeicons.FileCode
import compose.icons.lineawesomeicons.FileCodeSolid
import compose.icons.lineawesomeicons.FileExcelSolid
import compose.icons.lineawesomeicons.FileImage
import compose.icons.lineawesomeicons.FileImageSolid
import compose.icons.lineawesomeicons.FileInvoiceDollarSolid
import compose.icons.lineawesomeicons.FilePowerpoint
import compose.icons.lineawesomeicons.FileSolid
import compose.icons.lineawesomeicons.FileVideoSolid
import compose.icons.lineawesomeicons.FileWord
import compose.icons.lineawesomeicons.FingerprintSolid
import compose.icons.lineawesomeicons.Firefox
import compose.icons.lineawesomeicons.FirstOrderAlt
import compose.icons.lineawesomeicons.Flag
import compose.icons.lineawesomeicons.FlagCheckeredSolid
import compose.icons.lineawesomeicons.FlaskSolid
import compose.icons.lineawesomeicons.FolderOpenSolid
import compose.icons.lineawesomeicons.FolderPlusSolid
import compose.icons.lineawesomeicons.FontAwesomeFlag
import compose.icons.lineawesomeicons.Forumbee
import compose.icons.lineawesomeicons.FreeCodeCamp
import compose.icons.lineawesomeicons.FrownOpenSolid
import compose.icons.lineawesomeicons.FunnelDollarSolid
import compose.icons.lineawesomeicons.FutbolSolid
import compose.icons.lineawesomeicons.GenderlessSolid
import compose.icons.lineawesomeicons.GetPocket
import compose.icons.lineawesomeicons.GgCircle
import compose.icons.lineawesomeicons.GitAlt
import compose.icons.lineawesomeicons.Gitkraken
import compose.icons.lineawesomeicons.Gitlab
import compose.icons.lineawesomeicons.Gitter
import compose.icons.lineawesomeicons.GlassMartiniAltSolid
import compose.icons.lineawesomeicons.GlassesSolid
import compose.icons.lineawesomeicons.GlobeAsiaSolid
import compose.icons.lineawesomeicons.GlobeSolid
import compose.icons.lineawesomeicons.GolfBallSolid
import compose.icons.lineawesomeicons.Google
import compose.icons.lineawesomeicons.GoogleDrive
import compose.icons.lineawesomeicons.GooglePlusSquare
import compose.icons.lineawesomeicons.GopuramSolid
import compose.icons.lineawesomeicons.Grin
import compose.icons.lineawesomeicons.GrinHearts
import compose.icons.lineawesomeicons.GrinHeartsSolid
import compose.icons.lineawesomeicons.GrinSolid
import compose.icons.lineawesomeicons.GrinSquintTears
import compose.icons.lineawesomeicons.GrinStarsSolid
import compose.icons.lineawesomeicons.GrinTearsSolid
import compose.icons.lineawesomeicons.GrinTongueSolid
import compose.icons.lineawesomeicons.GrinTongueSquint
import compose.icons.lineawesomeicons.GrinTongueSquintSolid
import compose.icons.lineawesomeicons.GrinWink
import compose.icons.lineawesomeicons.Grunt
import compose.icons.lineawesomeicons.GuitarSolid
import compose.icons.lineawesomeicons.Gulp
import compose.icons.lineawesomeicons.HackerNews
import compose.icons.lineawesomeicons.HackerNewsSquare
import compose.icons.lineawesomeicons.Hackerrank
import compose.icons.lineawesomeicons.HamburgerSolid
import compose.icons.lineawesomeicons.HamsaSolid
import compose.icons.lineawesomeicons.HandHoldingHeartSolid
import compose.icons.lineawesomeicons.HandLizard
import compose.icons.lineawesomeicons.HandLizardSolid
import compose.icons.lineawesomeicons.HandPaper
import compose.icons.lineawesomeicons.HandPaperSolid
import compose.icons.lineawesomeicons.HandPeace
import compose.icons.lineawesomeicons.HandPeaceSolid
import compose.icons.lineawesomeicons.HandPointDownSolid
import compose.icons.lineawesomeicons.HandPointUp
import compose.icons.lineawesomeicons.HandRock
import compose.icons.lineawesomeicons.HandsHelpingSolid
import compose.icons.lineawesomeicons.Handshake
import compose.icons.lineawesomeicons.HaykalSolid
import compose.icons.lineawesomeicons.Hdd
import compose.icons.lineawesomeicons.HddSolid
import compose.icons.lineawesomeicons.HeadphonesAltSolid
import compose.icons.lineawesomeicons.HeadsetSolid
import compose.icons.lineawesomeicons.HeartbeatSolid
import compose.icons.lineawesomeicons.HelicopterSolid
import compose.icons.lineawesomeicons.HikingSolid
import compose.icons.lineawesomeicons.HippoSolid
import compose.icons.lineawesomeicons.HomeSolid
import compose.icons.lineawesomeicons.HorseHeadSolid
import compose.icons.lineawesomeicons.HorseSolid
import compose.icons.lineawesomeicons.HospitalAltSolid
import compose.icons.lineawesomeicons.HospitalSolid
import compose.icons.lineawesomeicons.HotelSolid
import compose.icons.lineawesomeicons.Hotjar
import compose.icons.lineawesomeicons.HourglassHalfSolid
import compose.icons.lineawesomeicons.HourglassSolid
import compose.icons.lineawesomeicons.HouseDamageSolid
import compose.icons.lineawesomeicons.Houzz
import compose.icons.lineawesomeicons.Hubspot
import compose.icons.lineawesomeicons.ICursorSolid
import compose.icons.lineawesomeicons.IceCreamSolid
import compose.icons.lineawesomeicons.IdBadgeSolid
import compose.icons.lineawesomeicons.Imdb
import compose.icons.lineawesomeicons.InfinitySolid
import compose.icons.lineawesomeicons.InfoSolid
import compose.icons.lineawesomeicons.Intercom
import compose.icons.lineawesomeicons.Invision
import compose.icons.lineawesomeicons.Ioxhost
import compose.icons.lineawesomeicons.JediOrder
import compose.icons.lineawesomeicons.Jenkins
import compose.icons.lineawesomeicons.JournalWhillsSolid
import compose.icons.lineawesomeicons.JsSquare
import compose.icons.lineawesomeicons.KhandaSolid
import compose.icons.lineawesomeicons.KissBeam
import compose.icons.lineawesomeicons.KissBeamSolid
import compose.icons.lineawesomeicons.KissWinkHeartSolid
import compose.icons.lineawesomeicons.LaptopMedicalSolid
import compose.icons.lineawesomeicons.LaptopSolid
import compose.icons.lineawesomeicons.Laravel
import compose.icons.lineawesomeicons.LaughSolid
import compose.icons.lineawesomeicons.LaughSquint
import compose.icons.lineawesomeicons.LayerGroupSolid
import compose.icons.lineawesomeicons.Leanpub
import compose.icons.lineawesomeicons.Lemon
import compose.icons.lineawesomeicons.LemonSolid
import compose.icons.lineawesomeicons.LessThanSolid
import compose.icons.lineawesomeicons.LifeRing
import compose.icons.lineawesomeicons.LifeRingSolid
import compose.icons.lineawesomeicons.ListOlSolid
import compose.icons.lineawesomeicons.LocationArrowSolid
import compose.icons.lineawesomeicons.LongArrowAltLeftSolid
import compose.icons.lineawesomeicons.MagicSolid
import compose.icons.lineawesomeicons.Mandalorian
import compose.icons.lineawesomeicons.MapMarkerSolid
import compose.icons.lineawesomeicons.MapSignsSolid
import compose.icons.lineawesomeicons.MaskSolid
import compose.icons.lineawesomeicons.Mastodon
import compose.icons.lineawesomeicons.Mdb
import compose.icons.lineawesomeicons.MediumM
import compose.icons.lineawesomeicons.MedkitSolid
import compose.icons.lineawesomeicons.Megaport
import compose.icons.lineawesomeicons.MehBlank
import compose.icons.lineawesomeicons.MehSolid
import compose.icons.lineawesomeicons.MemorySolid
import compose.icons.lineawesomeicons.MenorahSolid
import compose.icons.lineawesomeicons.MicrochipSolid
import compose.icons.lineawesomeicons.MicrophoneAltSlashSolid
import compose.icons.lineawesomeicons.Microsoft
import compose.icons.lineawesomeicons.MinusCircleSolid
import compose.icons.lineawesomeicons.MinusSolid
import compose.icons.lineawesomeicons.MinusSquareSolid
import compose.icons.lineawesomeicons.Mizuni
import compose.icons.lineawesomeicons.MoneyBillWaveAltSolid
import compose.icons.lineawesomeicons.MoneyBillWaveSolid
import compose.icons.lineawesomeicons.MoneyCheckAltSolid
import compose.icons.lineawesomeicons.MonumentSolid
import compose.icons.lineawesomeicons.Moon
import compose.icons.lineawesomeicons.MoonSolid
import compose.icons.lineawesomeicons.MousePointerSolid
import compose.icons.lineawesomeicons.MusicSolid
import compose.icons.lineawesomeicons.Neos
import compose.icons.lineawesomeicons.NetworkWiredSolid
import compose.icons.lineawesomeicons.NewspaperSolid
import compose.icons.lineawesomeicons.NotesMedicalSolid
import compose.icons.lineawesomeicons.Npm
import compose.icons.lineawesomeicons.ObjectUngroup
import compose.icons.lineawesomeicons.Odnoklassniki
import compose.icons.lineawesomeicons.OdnoklassnikiSquare
import compose.icons.lineawesomeicons.OmSolid
import compose.icons.lineawesomeicons.Opera
import compose.icons.lineawesomeicons.OptinMonster
import compose.icons.lineawesomeicons.Osi
import compose.icons.lineawesomeicons.PagerSolid
import compose.icons.lineawesomeicons.PaintBrushSolid
import compose.icons.lineawesomeicons.PalletSolid
import compose.icons.lineawesomeicons.PaperPlaneSolid
import compose.icons.lineawesomeicons.ParkingSolid
import compose.icons.lineawesomeicons.PassportSolid
import compose.icons.lineawesomeicons.PasteSolid
import compose.icons.lineawesomeicons.PauseSolid
import compose.icons.lineawesomeicons.PeaceSolid
import compose.icons.lineawesomeicons.PenSolid
import compose.icons.lineawesomeicons.PeopleCarrySolid
import compose.icons.lineawesomeicons.PepperHotSolid
import compose.icons.lineawesomeicons.Phabricator
import compose.icons.lineawesomeicons.PhoneAltSolid
import compose.icons.lineawesomeicons.PhoneSquareAltSolid
import compose.icons.lineawesomeicons.PhotoVideoSolid
import compose.icons.lineawesomeicons.PiggyBankSolid
import compose.icons.lineawesomeicons.PinterestP
import compose.icons.lineawesomeicons.PizzaSliceSolid
import compose.icons.lineawesomeicons.PlaceOfWorshipSolid
import compose.icons.lineawesomeicons.PlayCircle
import compose.icons.lineawesomeicons.PlaySolid
import compose.icons.lineawesomeicons.PlusSolid
import compose.icons.lineawesomeicons.PlusSquareSolid
import compose.icons.lineawesomeicons.PollHSolid
import compose.icons.lineawesomeicons.PooStormSolid
import compose.icons.lineawesomeicons.PoopSolid
import compose.icons.lineawesomeicons.PoundSignSolid
import compose.icons.lineawesomeicons.PrescriptionSolid
import compose.icons.lineawesomeicons.ProceduresSolid
import compose.icons.lineawesomeicons.ProductHunt
import compose.icons.lineawesomeicons.ProjectDiagramSolid
import compose.icons.lineawesomeicons.Python
import compose.icons.lineawesomeicons.QuestionCircle
import compose.icons.lineawesomeicons.Quora
import compose.icons.lineawesomeicons.Reacteurope
import compose.icons.lineawesomeicons.ReceiptSolid
import compose.icons.lineawesomeicons.RecycleSolid
import compose.icons.lineawesomeicons.Reddit
import compose.icons.lineawesomeicons.RedoAltSolid
import compose.icons.lineawesomeicons.Registered
import compose.icons.lineawesomeicons.RepublicanSolid
import compose.icons.lineawesomeicons.Researchgate
import compose.icons.lineawesomeicons.RestroomSolid
import compose.icons.lineawesomeicons.Rev
import compose.icons.lineawesomeicons.RibbonSolid
import compose.icons.lineawesomeicons.RobotSolid
import compose.icons.lineawesomeicons.RocketSolid
import compose.icons.lineawesomeicons.Rocketchat
import compose.icons.lineawesomeicons.RulerCombinedSolid
import compose.icons.lineawesomeicons.RulerSolid
import compose.icons.lineawesomeicons.RulerVerticalSolid
import compose.icons.lineawesomeicons.RunningSolid
import compose.icons.lineawesomeicons.Sass
import compose.icons.lineawesomeicons.SaveSolid
import compose.icons.lineawesomeicons.Scribd
import compose.icons.lineawesomeicons.ScrollSolid
import compose.icons.lineawesomeicons.SearchLocationSolid
import compose.icons.lineawesomeicons.Searchengin
import compose.icons.lineawesomeicons.SeedlingSolid
import compose.icons.lineawesomeicons.Sellsy
import compose.icons.lineawesomeicons.ServerSolid
import compose.icons.lineawesomeicons.ShapesSolid
import compose.icons.lineawesomeicons.ShareSolid
import compose.icons.lineawesomeicons.ShareSquareSolid
import compose.icons.lineawesomeicons.ShekelSignSolid
import compose.icons.lineawesomeicons.ShoppingBasketSolid
import compose.icons.lineawesomeicons.Shopware
import compose.icons.lineawesomeicons.ShuttleVanSolid
import compose.icons.lineawesomeicons.SignOutAltSolid
import compose.icons.lineawesomeicons.SignSolid
import compose.icons.lineawesomeicons.SignalSolid
import compose.icons.lineawesomeicons.SignatureSolid
import compose.icons.lineawesomeicons.SimCardSolid
import compose.icons.lineawesomeicons.Simplybuilt
import compose.icons.lineawesomeicons.Sith
import compose.icons.lineawesomeicons.SkullSolid
import compose.icons.lineawesomeicons.Skype
import compose.icons.lineawesomeicons.SlackHash
import compose.icons.lineawesomeicons.SlashSolid
import compose.icons.lineawesomeicons.SleighSolid
import compose.icons.lineawesomeicons.SmileSolid
import compose.icons.lineawesomeicons.SnapchatGhost
import compose.icons.lineawesomeicons.Snowflake
import compose.icons.lineawesomeicons.SnowflakeSolid
import compose.icons.lineawesomeicons.SortAlphaUpAltSolid
import compose.icons.lineawesomeicons.SortAlphaUpSolid
import compose.icons.lineawesomeicons.SortAmountUpSolid
import compose.icons.lineawesomeicons.SortDownSolid
import compose.icons.lineawesomeicons.SortNumericDownSolid
import compose.icons.lineawesomeicons.SortNumericUpAltSolid
import compose.icons.lineawesomeicons.SortSolid
import compose.icons.lineawesomeicons.Soundcloud
import compose.icons.lineawesomeicons.Sourcetree
import compose.icons.lineawesomeicons.SpaceShuttleSolid
import compose.icons.lineawesomeicons.SpeakerDeck
import compose.icons.lineawesomeicons.SpiderSolid
import compose.icons.lineawesomeicons.Spotify
import compose.icons.lineawesomeicons.SprayCanSolid
import compose.icons.lineawesomeicons.SquareRootAltSolid
import compose.icons.lineawesomeicons.Squarespace
import compose.icons.lineawesomeicons.StackExchange
import compose.icons.lineawesomeicons.StackOverflow
import compose.icons.lineawesomeicons.StampSolid
import compose.icons.lineawesomeicons.StarSolid
import compose.icons.lineawesomeicons.StepForwardSolid
import compose.icons.lineawesomeicons.StickyNoteSolid
import compose.icons.lineawesomeicons.StopCircle
import compose.icons.lineawesomeicons.StopCircleSolid
import compose.icons.lineawesomeicons.StopSolid
import compose.icons.lineawesomeicons.StoreAltSolid
import compose.icons.lineawesomeicons.StoreSolid
import compose.icons.lineawesomeicons.StreetViewSolid
import compose.icons.lineawesomeicons.Studiovinari
import compose.icons.lineawesomeicons.Stumbleupon
import compose.icons.lineawesomeicons.SuitcaseRollingSolid
import compose.icons.lineawesomeicons.SuitcaseSolid
import compose.icons.lineawesomeicons.SunSolid
import compose.icons.lineawesomeicons.Superpowers
import compose.icons.lineawesomeicons.SuperscriptSolid
import compose.icons.lineawesomeicons.Supple
import compose.icons.lineawesomeicons.SwatchbookSolid
import compose.icons.lineawesomeicons.SwimmingPoolSolid
import compose.icons.lineawesomeicons.Symfony
import compose.icons.lineawesomeicons.SyringeSolid
import compose.icons.lineawesomeicons.TableSolid
import compose.icons.lineawesomeicons.TabletAltSolid
import compose.icons.lineawesomeicons.TabletsSolid
import compose.icons.lineawesomeicons.TagsSolid
import compose.icons.lineawesomeicons.TasksSolid
import compose.icons.lineawesomeicons.Teamspeak
import compose.icons.lineawesomeicons.TheaterMasksSolid
import compose.icons.lineawesomeicons.Themeco
import compose.icons.lineawesomeicons.ThermometerFullSolid
import compose.icons.lineawesomeicons.ThermometerThreeQuartersSolid
import compose.icons.lineawesomeicons.ThumbsDown
import compose.icons.lineawesomeicons.ThumbsUpSolid
import compose.icons.lineawesomeicons.TintSolid
import compose.icons.lineawesomeicons.ToggleOffSolid
import compose.icons.lineawesomeicons.ToggleOnSolid
import compose.icons.lineawesomeicons.ToothSolid
import compose.icons.lineawesomeicons.ToriiGateSolid
import compose.icons.lineawesomeicons.TractorSolid
import compose.icons.lineawesomeicons.TrademarkSolid
import compose.icons.lineawesomeicons.TransgenderAltSolid
import compose.icons.lineawesomeicons.TrashAltSolid
import compose.icons.lineawesomeicons.TrashSolid
import compose.icons.lineawesomeicons.TruckLoadingSolid
import compose.icons.lineawesomeicons.TruckMonsterSolid
import compose.icons.lineawesomeicons.TruckMovingSolid
import compose.icons.lineawesomeicons.Tumblr
import compose.icons.lineawesomeicons.TumblrSquare
import compose.icons.lineawesomeicons.Ubuntu
import compose.icons.lineawesomeicons.UmbrellaBeachSolid
import compose.icons.lineawesomeicons.UnderlineSolid
import compose.icons.lineawesomeicons.UniversitySolid
import compose.icons.lineawesomeicons.UnlinkSolid
import compose.icons.lineawesomeicons.UnlockAltSolid
import compose.icons.lineawesomeicons.UnlockSolid
import compose.icons.lineawesomeicons.Usb
import compose.icons.lineawesomeicons.UserAltSolid
import compose.icons.lineawesomeicons.UserCircleSolid
import compose.icons.lineawesomeicons.UserCogSolid
import compose.icons.lineawesomeicons.UserMdSolid
import compose.icons.lineawesomeicons.UserNurseSolid
import compose.icons.lineawesomeicons.UserShieldSolid
import compose.icons.lineawesomeicons.UserTimesSolid
import compose.icons.lineawesomeicons.UsersCogSolid
import compose.icons.lineawesomeicons.VenusDoubleSolid
import compose.icons.lineawesomeicons.VenusSolid
import compose.icons.lineawesomeicons.Viadeo
import compose.icons.lineawesomeicons.ViadeoSquare
import compose.icons.lineawesomeicons.VialSolid
import compose.icons.lineawesomeicons.VideoSlashSolid
import compose.icons.lineawesomeicons.VimeoSquare
import compose.icons.lineawesomeicons.VimeoV
import compose.icons.lineawesomeicons.Vine
import compose.icons.lineawesomeicons.VolumeMuteSolid
import compose.icons.lineawesomeicons.WalkingSolid
import compose.icons.lineawesomeicons.WaveSquareSolid
import compose.icons.lineawesomeicons.Weibo
import compose.icons.lineawesomeicons.WeightHangingSolid
import compose.icons.lineawesomeicons.Weixin
import compose.icons.lineawesomeicons.WindowClose
import compose.icons.lineawesomeicons.WindowCloseSolid
import compose.icons.lineawesomeicons.WindowMinimize
import compose.icons.lineawesomeicons.WindowMinimizeSolid
import compose.icons.lineawesomeicons.WindowRestoreSolid
import compose.icons.lineawesomeicons.WolfPackBattalion
import compose.icons.lineawesomeicons.WordpressSimple
import compose.icons.lineawesomeicons.Wpbeginner
import compose.icons.lineawesomeicons.Wpforms
import compose.icons.lineawesomeicons.Wpressr
import compose.icons.lineawesomeicons.Xbox
import compose.icons.lineawesomeicons.Yandex
import compose.icons.lineawesomeicons.YandexInternational
import compose.icons.lineawesomeicons.YinYangSolid
import compose.icons.lineawesomeicons.YoutubeSquare
import org.jsoup.nodes.Node

object Icon : Base() {
    override val name: String = "icon"

    @Composable
    override fun View(node: Node) {
        val iconType = when (node.attr("type").lowercase()) {
            "accountbox" -> Icons.Filled.AccountBox
            "accountcircle" -> Icons.Filled.AccountCircle
            "addcircle" -> Icons.Filled.AddCircle
            "add" -> Icons.Filled.Add
            "arrowback" -> Icons.Filled.ArrowBack
            "arrowdropdown" -> Icons.Filled.ArrowDropDown
            "arrowforward" -> Icons.Filled.ArrowForward
            "build" -> Icons.Filled.Build
            "call" -> Icons.Filled.Call
            "checkcircle" -> Icons.Filled.CheckCircle
            "check" -> Icons.Filled.Check
            "clear" -> Icons.Filled.Clear
            "close" -> Icons.Filled.Close
            "create" -> Icons.Filled.Create
            "daterange" -> Icons.Filled.DateRange
            "delete" -> Icons.Filled.Delete
            "done" -> Icons.Filled.Done
            "email" -> Icons.Filled.Email
            "exittoapp" -> Icons.Filled.ExitToApp
            "face" -> Icons.Filled.Face
            "favoriteborder" -> Icons.Filled.FavoriteBorder
            "favorite" -> Icons.Filled.Favorite
            "home" -> Icons.Filled.Home
            "info" -> Icons.Filled.Info
            "keyboardarrowdown" -> Icons.Filled.KeyboardArrowDown
            "keyboardarrowleft" -> Icons.Filled.KeyboardArrowLeft
            "keyboardarrowright" -> Icons.Filled.KeyboardArrowRight
            "keyboardarrowup" -> Icons.Filled.KeyboardArrowUp
            "list" -> Icons.Filled.List
            "locationon" -> Icons.Filled.LocationOn
            "lock" -> Icons.Filled.Lock
            "mailoutline" -> Icons.Outlined.MailOutline
            "menu" -> Icons.Filled.Menu
            "morevert" -> Icons.Filled.MoreVert
            "notifications" -> Icons.Filled.Notifications
            "person" -> Icons.Filled.Person
            "phone" -> Icons.Filled.Phone
            "place" -> Icons.Filled.Place
            "playarrow" -> Icons.Filled.PlayArrow
            "refresh" -> Icons.Filled.Refresh
            "search" -> Icons.Filled.Search
            "send" -> Icons.Filled.Send
            "settings" -> Icons.Filled.Settings
            "share" -> Icons.Filled.Share
            "shoppingcart" -> Icons.Filled.ShoppingCart
            "star" -> Icons.Filled.Star
            "thumbup" -> Icons.Filled.ThumbUp
            "warning" -> Icons.Filled.Warning
            "cookiebitesolid" -> LineAwesomeIcons.CookieBiteSolid
            "playcircle" -> LineAwesomeIcons.PlayCircle
            "wordpresssimple" -> LineAwesomeIcons.WordpressSimple
            "storealtsolid" -> LineAwesomeIcons.StoreAltSolid
            "dizzy" -> LineAwesomeIcons.Dizzy
            "journalwhills" -> LineAwesomeIcons.JournalWhillsSolid
            "bookdeadsolid" -> LineAwesomeIcons.BookDeadSolid
            "photovideosolid" -> LineAwesomeIcons.PhotoVideoSolid
            "hipposolid" -> LineAwesomeIcons.HippoSolid
            "calendarchecksolid" -> LineAwesomeIcons.CalendarCheckSolid
            "bikingsolid" -> LineAwesomeIcons.BikingSolid
            "arrowaltcirclerightsolid" -> LineAwesomeIcons.ArrowAltCircleRightSolid
            "buffer" -> LineAwesomeIcons.Buffer
            "cloudsmith" -> LineAwesomeIcons.Cloudsmith
            "viadeo" -> LineAwesomeIcons.Viadeo
            "handpointdownsolid" -> LineAwesomeIcons.HandPointDownSolid
            "hospitalsolid" -> LineAwesomeIcons.HospitalSolid
            "stackoverflow" -> LineAwesomeIcons.StackOverflow
            "calendarplussolid" -> LineAwesomeIcons.CalendarPlusSolid
            "icecreamsolid" -> LineAwesomeIcons.IceCreamSolid
            "superscriptsolid" -> LineAwesomeIcons.SuperscriptSolid
            "mehblank" -> LineAwesomeIcons.MehBlank
            "frownopensolid" -> LineAwesomeIcons.FrownOpenSolid
            "campgroundsolid" -> LineAwesomeIcons.CampgroundSolid
            "menorahsolid" -> LineAwesomeIcons.MenorahSolid
            "eyeslashsolid" -> LineAwesomeIcons.EyeSlashSolid
            "signsolid" -> LineAwesomeIcons.SignSolid
            "batteryemptysolid" -> LineAwesomeIcons.BatteryEmptySolid
            "python" -> LineAwesomeIcons.Python
            "sortnumericupaltsolid" -> LineAwesomeIcons.SortNumericUpAltSolid
            "fileaudiosolid" -> LineAwesomeIcons.FileAudioSolid
            "seedlingsolid" -> LineAwesomeIcons.SeedlingSolid
            "universitysolid" -> LineAwesomeIcons.UniversitySolid
            "dochub" -> LineAwesomeIcons.Dochub
            "ember" -> LineAwesomeIcons.Ember
            "longarrowaltleftsolid" -> LineAwesomeIcons.LongArrowAltLeftSolid
            "discord" -> LineAwesomeIcons.Discord
            "feathersolid" -> LineAwesomeIcons.FeatherSolid
            "trashsolid" -> LineAwesomeIcons.TrashSolid
            "pastesolid" -> LineAwesomeIcons.PasteSolid
            "coinssolid" -> LineAwesomeIcons.CoinsSolid
            "usernursesolid" -> LineAwesomeIcons.UserNurseSolid
            "cloudsolid" -> LineAwesomeIcons.CloudSolid
            "grin" -> LineAwesomeIcons.Grin
            "sortdownsolid" -> LineAwesomeIcons.SortDownSolid
            "laptopmedicalsolid" -> LineAwesomeIcons.LaptopMedicalSolid
            "filearchive" -> LineAwesomeIcons.FileArchive
            "busaltsolid" -> LineAwesomeIcons.BusAltSolid
            "youtubesquare" -> LineAwesomeIcons.YoutubeSquare
            "hikingsolid" -> LineAwesomeIcons.HikingSolid
            "odnoklassniki" -> LineAwesomeIcons.Odnoklassniki
            "theatermaskssolid" -> LineAwesomeIcons.TheaterMasksSolid
            "deskpro" -> LineAwesomeIcons.Deskpro
            "cloudmoonrainsolid" -> LineAwesomeIcons.CloudMoonRainSolid
            "skullsolid" -> LineAwesomeIcons.SkullSolid
            "parkingsolid" -> LineAwesomeIcons.ParkingSolid
            "republicansolid" -> LineAwesomeIcons.RepublicanSolid
            "toggleoffsolid" -> LineAwesomeIcons.ToggleOffSolid
            "creativecommonsnd" -> LineAwesomeIcons.CreativeCommonsNd
            "bullseyesolid" -> LineAwesomeIcons.BullseyeSolid
            "faxsolid" -> LineAwesomeIcons.FaxSolid
            "sortalphaupaltsolid" -> LineAwesomeIcons.SortAlphaUpAltSolid
            "windowminimizesolid" -> LineAwesomeIcons.WindowMinimizeSolid
            "sellsy" -> LineAwesomeIcons.Sellsy
            "moneybillwavesolid" -> LineAwesomeIcons.MoneyBillWaveSolid
            "binocularssolid" -> LineAwesomeIcons.BinocularsSolid
            "rulercombinedsolid" -> LineAwesomeIcons.RulerCombinedSolid
            "grintonguesquint" -> LineAwesomeIcons.GrinTongueSquint
            "dungeonsolid" -> LineAwesomeIcons.DungeonSolid
            "tintsolid" -> LineAwesomeIcons.TintSolid
            "thermometerfullsolid" -> LineAwesomeIcons.ThermometerFullSolid
            "hotjar" -> LineAwesomeIcons.Hotjar
            "scribd" -> LineAwesomeIcons.Scribd
            "dumpstersolid" -> LineAwesomeIcons.DumpsterSolid
            "mandalorian" -> LineAwesomeIcons.Mandalorian
            "pausesolid" -> LineAwesomeIcons.PauseSolid
            "externallinkaltsolid" -> LineAwesomeIcons.ExternalLinkAltSolid
            "pensolid" -> LineAwesomeIcons.PenSolid
            "dicesixsolid" -> LineAwesomeIcons.DiceSixSolid
            "spaceshuttlesolid" -> LineAwesomeIcons.SpaceShuttleSolid
            "housedamagesolid" -> LineAwesomeIcons.HouseDamageSolid
            "signalsolid" -> LineAwesomeIcons.SignalSolid
            "kissbeam" -> LineAwesomeIcons.KissBeam
            "googledrive" -> LineAwesomeIcons.GoogleDrive
            "newspapersolid" -> LineAwesomeIcons.NewspaperSolid
            "exclamationtrianglesolid" -> LineAwesomeIcons.ExclamationTriangleSolid
            "cloudshowersheavysolid" -> LineAwesomeIcons.CloudShowersHeavySolid
            "carsolid" -> LineAwesomeIcons.CarSolid
            "skype" -> LineAwesomeIcons.Skype
            "digg" -> LineAwesomeIcons.Digg
            "jenkins" -> LineAwesomeIcons.Jenkins
            "carcrashsolid" -> LineAwesomeIcons.CarCrashSolid
            "peacesolid" -> LineAwesomeIcons.PeaceSolid
            "baseballballsolid" -> LineAwesomeIcons.BaseballBallSolid
            "plussquaresolid" -> LineAwesomeIcons.PlusSquareSolid
            "osi" -> LineAwesomeIcons.Osi
            "minuscirclesolid" -> LineAwesomeIcons.MinusCircleSolid
            "tumblr" -> LineAwesomeIcons.Tumblr
            "folderplussolid" -> LineAwesomeIcons.FolderPlusSolid
            "receiptsolid" -> LineAwesomeIcons.ReceiptSolid
            "bitcoin" -> LineAwesomeIcons.Bitcoin
            "golfballsolid" -> LineAwesomeIcons.GolfBallSolid
            "underlinesolid" -> LineAwesomeIcons.UnderlineSolid
            "pepperhotsolid" -> LineAwesomeIcons.PepperHotSolid
            "handpapersolid" -> LineAwesomeIcons.HandPaperSolid
            "videoslashsolid" -> LineAwesomeIcons.VideoSlashSolid
            "hourglasssolid" -> LineAwesomeIcons.HourglassSolid
            "facebooksquare" -> LineAwesomeIcons.FacebookSquare
            "signoutaltsolid" -> LineAwesomeIcons.SignOutAltSolid
            "piggybanksolid" -> LineAwesomeIcons.PiggyBankSolid
            "firstorderalt" -> LineAwesomeIcons.FirstOrderAlt
            "toggleonsolid" -> LineAwesomeIcons.ToggleOnSolid
            "shopware" -> LineAwesomeIcons.Shopware
            "drumsolid" -> LineAwesomeIcons.DrumSolid
            "artstation" -> LineAwesomeIcons.Artstation
            "airfreshener" -> LineAwesomeIcons.AirFreshenerSolid
            "handshelpingsolid" -> LineAwesomeIcons.HandsHelpingSolid
            "tagssolid" -> LineAwesomeIcons.TagsSolid
            "facebookf" -> LineAwesomeIcons.FacebookF
            "ggcircle" -> LineAwesomeIcons.GgCircle
            "cartplussolid" -> LineAwesomeIcons.CartPlusSolid
            "ccdiscover" -> LineAwesomeIcons.CcDiscover
            "ellipsisvsolid" -> LineAwesomeIcons.EllipsisVSolid
            "savesolid" -> LineAwesomeIcons.SaveSolid
            "hotelsolid" -> LineAwesomeIcons.HotelSolid
            "truckloadingsolid" -> LineAwesomeIcons.TruckLoadingSolid
            "handpeace" -> LineAwesomeIcons.HandPeace
            "slashsolid" -> LineAwesomeIcons.SlashSolid
            "chartpiesolid" -> LineAwesomeIcons.ChartPieSolid
            "musicsolid" -> LineAwesomeIcons.MusicSolid
            "studiovinari" -> LineAwesomeIcons.Studiovinari
            "dropbox" -> LineAwesomeIcons.Dropbox
            "envelopesquaresolid" -> LineAwesomeIcons.EnvelopeSquareSolid
            "unlockaltsolid" -> LineAwesomeIcons.UnlockAltSolid
            "stepforwardsolid" -> LineAwesomeIcons.StepForwardSolid
            "grintearssolid" -> LineAwesomeIcons.GrinTearsSolid
            "airbnb" -> LineAwesomeIcons.Airbnb
            "medkitsolid" -> LineAwesomeIcons.MedkitSolid
            "megaport" -> LineAwesomeIcons.Megaport
            "laptopsolid" -> LineAwesomeIcons.LaptopSolid
            "notesmedicalsolid" -> LineAwesomeIcons.NotesMedicalSolid
            "assistivelisteningsystemssolid" -> LineAwesomeIcons.AssistiveListeningSystemsSolid
            "affiliatetheme" -> LineAwesomeIcons.Affiliatetheme
            "hackerrank" -> LineAwesomeIcons.Hackerrank
            "sass" -> LineAwesomeIcons.Sass
            "fileinvoicedollarsolid" -> LineAwesomeIcons.FileInvoiceDollarSolid
            "robotsolid" -> LineAwesomeIcons.RobotSolid
            "spidersolid" -> LineAwesomeIcons.SpiderSolid
            "wpressr" -> LineAwesomeIcons.Wpressr
            "shekelsignsolid" -> LineAwesomeIcons.ShekelSignSolid
            "jediorder" -> LineAwesomeIcons.JediOrder
            "userscogsolid" -> LineAwesomeIcons.UsersCogSolid
            "hackernewssquare" -> LineAwesomeIcons.HackerNewsSquare
            "yinyangsolid" -> LineAwesomeIcons.YinYangSolid
            "suitcasesolid" -> LineAwesomeIcons.SuitcaseSolid
            "gitalt" -> LineAwesomeIcons.GitAlt
            "filecode" -> LineAwesomeIcons.FileCode
            "dogsolid" -> LineAwesomeIcons.DogSolid
            "signaturesolid" -> LineAwesomeIcons.SignatureSolid
            "handholdingheartsolid" -> LineAwesomeIcons.HandHoldingHeartSolid
            "handpeacesolid" -> LineAwesomeIcons.HandPeaceSolid
            "superpowers" -> LineAwesomeIcons.Superpowers
            "vialsolid" -> LineAwesomeIcons.VialSolid
            "phonesquarealtsolid" -> LineAwesomeIcons.PhoneSquareAltSolid
            "squarespace" -> LineAwesomeIcons.Squarespace
            "battlenet" -> LineAwesomeIcons.BattleNet
            "biohazardsolid" -> LineAwesomeIcons.BiohazardSolid
            "microsoft" -> LineAwesomeIcons.Microsoft
            "sharesquaresolid" -> LineAwesomeIcons.ShareSquareSolid
            "angry" -> LineAwesomeIcons.Angry
            "trashaltsolid" -> LineAwesomeIcons.TrashAltSolid
            "thumbsdown" -> LineAwesomeIcons.ThumbsDown
            "brainsolid" -> LineAwesomeIcons.BrainSolid
            "googleplussquare" -> LineAwesomeIcons.GooglePlusSquare
            "viadeosquare" -> LineAwesomeIcons.ViadeoSquare
            "spraycansolid" -> LineAwesomeIcons.SprayCanSolid
            "brushsolid" -> LineAwesomeIcons.BrushSolid
            "ccpaypal" -> LineAwesomeIcons.CcPaypal
            "chessboardsolid" -> LineAwesomeIcons.ChessBoardSolid
            "chessrooksolid" -> LineAwesomeIcons.ChessRookSolid
            "speakerdeck" -> LineAwesomeIcons.SpeakerDeck
            "atlassian" -> LineAwesomeIcons.Atlassian
            "umbrellabeachsolid" -> LineAwesomeIcons.UmbrellaBeachSolid
            "cogssolid" -> LineAwesomeIcons.CogsSolid
            "simcardsolid" -> LineAwesomeIcons.SimCardSolid
            "caretsquareleftsolid" -> LineAwesomeIcons.CaretSquareLeftSolid
            "commentdots" -> LineAwesomeIcons.CommentDots
            "reacteurope" -> LineAwesomeIcons.Reacteurope
            "unlocksolid" -> LineAwesomeIcons.UnlockSolid
            "deafsolid" -> LineAwesomeIcons.DeafSolid
            "yandexinternational" -> LineAwesomeIcons.YandexInternational
            "citysolid" -> LineAwesomeIcons.CitySolid
            "plussolid" -> LineAwesomeIcons.PlusSolid
            "arrowdownsolid" -> LineAwesomeIcons.ArrowDownSolid
            "amazon" -> LineAwesomeIcons.Amazon
            "cubesolid" -> LineAwesomeIcons.CubeSolid
            "hdd" -> LineAwesomeIcons.Hdd
            "snowflake" -> LineAwesomeIcons.Snowflake
            "cubessolid" -> LineAwesomeIcons.CubesSolid
            "gopuramsolid" -> LineAwesomeIcons.GopuramSolid
            "grintonguesolid" -> LineAwesomeIcons.GrinTongueSolid
            "buynlarge" -> LineAwesomeIcons.BuyNLarge
            "borderstylesolid" -> LineAwesomeIcons.BorderStyleSolid
            "chalkboardteachersolid" -> LineAwesomeIcons.ChalkboardTeacherSolid
            "trademarksolid" -> LineAwesomeIcons.TrademarkSolid
            "dev" -> LineAwesomeIcons.Dev
            "closedcaptioningsolid" -> LineAwesomeIcons.ClosedCaptioningSolid
            "wavesquaresolid" -> LineAwesomeIcons.WaveSquareSolid
            "usercogsolid" -> LineAwesomeIcons.UserCogSolid
            "vimeosquare" -> LineAwesomeIcons.VimeoSquare
            "syringesolid" -> LineAwesomeIcons.SyringeSolid
            "carbatterysolid" -> LineAwesomeIcons.CarBatterySolid
            "filealt" -> LineAwesomeIcons.FileAlt
            "phonealtsolid" -> LineAwesomeIcons.PhoneAltSolid
            "slackhash" -> LineAwesomeIcons.SlackHash
            "starsolid" -> LineAwesomeIcons.StarSolid
            "facebookmessenger" -> LineAwesomeIcons.FacebookMessenger
            "earlybirds" -> LineAwesomeIcons.Earlybirds
            "npm" -> LineAwesomeIcons.Npm
            "heartbeatsolid" -> LineAwesomeIcons.HeartbeatSolid
            "handpaper" -> LineAwesomeIcons.HandPaper
            "asymmetrik" -> LineAwesomeIcons.Asymmetrik
            "briefcasesolid" -> LineAwesomeIcons.BriefcaseSolid
            "crosshairssolid" -> LineAwesomeIcons.CrosshairsSolid
            "laughsquint" -> LineAwesomeIcons.LaughSquint
            "circle" -> LineAwesomeIcons.Circle
            "stopcirclesolid" -> LineAwesomeIcons.StopCircleSolid
            "dicethreesolid" -> LineAwesomeIcons.DiceThreeSolid
            "runningsolid" -> LineAwesomeIcons.RunningSolid
            "transgenderaltsolid" -> LineAwesomeIcons.TransgenderAltSolid
            "leanpub" -> LineAwesomeIcons.Leanpub
            "handlizard" -> LineAwesomeIcons.HandLizard
            "filecodesolid" -> LineAwesomeIcons.FileCodeSolid
            "arrowupsolid" -> LineAwesomeIcons.ArrowUpSolid
            "checkdoublesolid" -> LineAwesomeIcons.CheckDoubleSolid
            "mastodon" -> LineAwesomeIcons.Mastodon
            "sunsolid" -> LineAwesomeIcons.SunSolid
            "sortsolid" -> LineAwesomeIcons.SortSolid
            "equalssolid" -> LineAwesomeIcons.EqualsSolid
            "squarerootaltsolid" -> LineAwesomeIcons.SquareRootAltSolid
            "adsolid" -> LineAwesomeIcons.AdSolid
            "bookopensolid" -> LineAwesomeIcons.BookOpenSolid
            "caretsquaredownsolid" -> LineAwesomeIcons.CaretSquareDownSolid
            "scrollsolid" -> LineAwesomeIcons.ScrollSolid
            "storesolid" -> LineAwesomeIcons.StoreSolid
            "chevronleftsolid" -> LineAwesomeIcons.ChevronLeftSolid
            "taskssolid" -> LineAwesomeIcons.TasksSolid
            "ccamex" -> LineAwesomeIcons.CcAmex
            "rulersolid" -> LineAwesomeIcons.RulerSolid
            "proceduressolid" -> LineAwesomeIcons.ProceduresSolid
            "researchgate" -> LineAwesomeIcons.Researchgate
            "kissbeamsolid" -> LineAwesomeIcons.KissBeamSolid
            "bugsolid" -> LineAwesomeIcons.BugSolid
            "grinhearts" -> LineAwesomeIcons.GrinHearts
            "carrotsolid" -> LineAwesomeIcons.CarrotSolid
            "grinsquinttears" -> LineAwesomeIcons.GrinSquintTears
            "networkwiredsolid" -> LineAwesomeIcons.NetworkWiredSolid
            "horsesolid" -> LineAwesomeIcons.HorseSolid
            "lessthansolid" -> LineAwesomeIcons.LessThanSolid
            "forumbee" -> LineAwesomeIcons.Forumbee
            "stopsolid" -> LineAwesomeIcons.StopSolid
            "angledoubleupsolid" -> LineAwesomeIcons.AngleDoubleUpSolid
            "infinitysolid" -> LineAwesomeIcons.InfinitySolid
            "xbox" -> LineAwesomeIcons.Xbox
            "quora" -> LineAwesomeIcons.Quora
            "dollyflatbedsolid" -> LineAwesomeIcons.DollyFlatbedSolid
            "asterisksolid" -> LineAwesomeIcons.AsteriskSolid
            "moonsolid" -> LineAwesomeIcons.MoonSolid
            "registered" -> LineAwesomeIcons.Registered
            "dandd" -> LineAwesomeIcons.DAndD
            "headsetsolid" -> LineAwesomeIcons.HeadsetSolid
            "gitlab" -> LineAwesomeIcons.Gitlab
            "calendarsolid" -> LineAwesomeIcons.CalendarSolid
            "spotify" -> LineAwesomeIcons.Spotify
            "etsy" -> LineAwesomeIcons.Etsy
            "cashregistersolid" -> LineAwesomeIcons.CashRegisterSolid
            "bussolid" -> LineAwesomeIcons.BusSolid
            "volumemutesolid" -> LineAwesomeIcons.VolumeMuteSolid
            "boldsolid" -> LineAwesomeIcons.BoldSolid
            "useraltsolid" -> LineAwesomeIcons.UserAltSolid
            "hackernews" -> LineAwesomeIcons.HackerNews
            "dnasolid" -> LineAwesomeIcons.DnaSolid
            "lemon" -> LineAwesomeIcons.Lemon
            "bellslash" -> LineAwesomeIcons.BellSlash
            "stampsolid" -> LineAwesomeIcons.StampSolid
            "fileword" -> LineAwesomeIcons.FileWord
            "hospitalaltsolid" -> LineAwesomeIcons.HospitalAltSolid
            "borderallsolid" -> LineAwesomeIcons.BorderAllSolid
            "pagersolid" -> LineAwesomeIcons.PagerSolid
            "capsulessolid" -> LineAwesomeIcons.CapsulesSolid
            "weixin" -> LineAwesomeIcons.Weixin
            "searchlocationsolid" -> LineAwesomeIcons.SearchLocationSolid
            "angledoubleleftsolid" -> LineAwesomeIcons.AngleDoubleLeftSolid
            "biblesolid" -> LineAwesomeIcons.BibleSolid
            "peoplecarrysolid" -> LineAwesomeIcons.PeopleCarrySolid
            "unlinksolid" -> LineAwesomeIcons.UnlinkSolid
            "caretsquareup" -> LineAwesomeIcons.CaretSquareUp
            "imdb" -> LineAwesomeIcons.Imdb
            "pinterestp" -> LineAwesomeIcons.PinterestP
            "ioxhost" -> LineAwesomeIcons.Ioxhost
            "angledoublerightsolid" -> LineAwesomeIcons.AngleDoubleRightSolid
            "beziercurvesolid" -> LineAwesomeIcons.BezierCurveSolid
            "windowclosesolid" -> LineAwesomeIcons.WindowCloseSolid
            "usertimessolid" -> LineAwesomeIcons.UserTimesSolid
            "projectdiagramsolid" -> LineAwesomeIcons.ProjectDiagramSolid
            "clipboardchecksolid" -> LineAwesomeIcons.ClipboardCheckSolid
            "monumentsolid" -> LineAwesomeIcons.MonumentSolid
            "phabricator" -> LineAwesomeIcons.Phabricator
            "tablesolid" -> LineAwesomeIcons.TableSolid
            "envelopeopentextsolid" -> LineAwesomeIcons.EnvelopeOpenTextSolid
            "burnsolid" -> LineAwesomeIcons.BurnSolid
            "filesolid" -> LineAwesomeIcons.FileSolid
            "odnoklassnikisquare" -> LineAwesomeIcons.OdnoklassnikiSquare
            "grinheartssolid" -> LineAwesomeIcons.GrinHeartsSolid
            "democratsolid" -> LineAwesomeIcons.DemocratSolid
            "laughsolid" -> LineAwesomeIcons.LaughSolid
            "rev" -> LineAwesomeIcons.Rev
            "toriigatesolid" -> LineAwesomeIcons.ToriiGateSolid
            "moon" -> LineAwesomeIcons.Moon
            "magicsolid" -> LineAwesomeIcons.MagicSolid
            "microchipsolid" -> LineAwesomeIcons.MicrochipSolid
            "mapmarkersolid" -> LineAwesomeIcons.MapMarkerSolid
            "chartbar" -> LineAwesomeIcons.ChartBar
            "passportsolid" -> LineAwesomeIcons.PassportSolid
            "usercirclesolid" -> LineAwesomeIcons.UserCircleSolid
            "ebay" -> LineAwesomeIcons.Ebay
            "playsolid" -> LineAwesomeIcons.PlaySolid
            "searchengin" -> LineAwesomeIcons.Searchengin
            "balancescalesolid" -> LineAwesomeIcons.BalanceScaleSolid
            "icursorsolid" -> LineAwesomeIcons.ICursorSolid
            "envelopesolid" -> LineAwesomeIcons.EnvelopeSolid
            "btc" -> LineAwesomeIcons.Btc
            "clock" -> LineAwesomeIcons.Clock
            "atlassolid" -> LineAwesomeIcons.AtlasSolid
            "opera" -> LineAwesomeIcons.Opera
            "moneybillwavealtsolid" -> LineAwesomeIcons.MoneyBillWaveAltSolid
            "filevideosolid" -> LineAwesomeIcons.FileVideoSolid
            "wpbeginner" -> LineAwesomeIcons.Wpbeginner
            "streetviewsolid" -> LineAwesomeIcons.StreetViewSolid
            "sith" -> LineAwesomeIcons.Sith
            "freecodecamp" -> LineAwesomeIcons.FreeCodeCamp
            "hddsolid" -> LineAwesomeIcons.HddSolid
            "simplybuilt" -> LineAwesomeIcons.Simplybuilt
            "caretupsolid" -> LineAwesomeIcons.CaretUpSolid
            "commentsolid" -> LineAwesomeIcons.CommentSolid
            "fileexcelsolid" -> LineAwesomeIcons.FileExcelSolid
            "tumblrsquare" -> LineAwesomeIcons.TumblrSquare
            "minussolid" -> LineAwesomeIcons.MinusSolid
            "edge" -> LineAwesomeIcons.Edge
            "exchangealtsolid" -> LineAwesomeIcons.ExchangeAltSolid
            "grinstarssolid" -> LineAwesomeIcons.GrinStarsSolid
            "serversolid" -> LineAwesomeIcons.ServerSolid
            "erasersolid" -> LineAwesomeIcons.EraserSolid
            "checkcirclesolid" -> LineAwesomeIcons.CheckCircleSolid
            "venussolid" -> LineAwesomeIcons.VenusSolid
            "firefox" -> LineAwesomeIcons.Firefox
            "vimeov" -> LineAwesomeIcons.VimeoV
            "baconsolid" -> LineAwesomeIcons.BaconSolid
            "amilia" -> LineAwesomeIcons.Amilia
            "sourcetree" -> LineAwesomeIcons.Sourcetree
            "dribbblesquare" -> LineAwesomeIcons.DribbbleSquare
            "mizuni" -> LineAwesomeIcons.Mizuni
            "hamsasolid" -> LineAwesomeIcons.HamsaSolid
            "minussquaresolid" -> LineAwesomeIcons.MinusSquareSolid
            "checksolid" -> LineAwesomeIcons.CheckSolid
            "paperplanesolid" -> LineAwesomeIcons.PaperPlaneSolid
            "vine" -> LineAwesomeIcons.Vine
            "cannabiss" -> LineAwesomeIcons.CannabisSolid
            "houzz" -> LineAwesomeIcons.Houzz
            "smilesolid" -> LineAwesomeIcons.SmileSolid
            "fileimage" -> LineAwesomeIcons.FileImage
            "usb" -> LineAwesomeIcons.Usb
            "swatchbooksolid" -> LineAwesomeIcons.SwatchbookSolid
            "laravel" -> LineAwesomeIcons.Laravel
            "exclamationcirclesolid" -> LineAwesomeIcons.ExclamationCircleSolid
            "grintonguesquintsolid" -> LineAwesomeIcons.GrinTongueSquintSolid
            "chargingstationsolid" -> LineAwesomeIcons.ChargingStationSolid
            "poundsignsolid" -> LineAwesomeIcons.PoundSignSolid
            "arrowaltcircleup" -> LineAwesomeIcons.ArrowAltCircleUp
            "fontawesomeflag" -> LineAwesomeIcons.FontAwesomeFlag
            "ubuntu" -> LineAwesomeIcons.Ubuntu
            "listolsolid" -> LineAwesomeIcons.ListOlSolid
            "grinwink" -> LineAwesomeIcons.GrinWink
            "omsolid" -> LineAwesomeIcons.OmSolid
            "arrowleftsolid" -> LineAwesomeIcons.ArrowLeftSolid
            "digitaltachographsolid" -> LineAwesomeIcons.DigitalTachographSolid
            "batterythreequarterssolid" -> LineAwesomeIcons.BatteryThreeQuartersSolid
            "audiodescriptionsolid" -> LineAwesomeIcons.AudioDescriptionSolid
            "thermometerthreequarterssolid" -> LineAwesomeIcons.ThermometerThreeQuartersSolid
            "sharesolid" -> LineAwesomeIcons.ShareSolid
            "glassessolid" -> LineAwesomeIcons.GlassesSolid
            "deviantart" -> LineAwesomeIcons.Deviantart
            "windowclose" -> LineAwesomeIcons.WindowClose
            "dyalog" -> LineAwesomeIcons.Dyalog
            "cloudsunrainsolid" -> LineAwesomeIcons.CloudSunRainSolid
            "draftingcompasssolid" -> LineAwesomeIcons.DraftingCompassSolid
            "weibo" -> LineAwesomeIcons.Weibo
            "rocketsolid" -> LineAwesomeIcons.RocketSolid
            "cloudsunsolid" -> LineAwesomeIcons.CloudSunSolid
            "producthunt" -> LineAwesomeIcons.ProductHunt
            "idbadgesolid" -> LineAwesomeIcons.IdBadgeSolid
            "tractorsolid" -> LineAwesomeIcons.TractorSolid
            "basketballballsolid" -> LineAwesomeIcons.BasketballBallSolid
            "genderlesssolid" -> LineAwesomeIcons.GenderlessSolid
            "ellipsis" -> LineAwesomeIcons.EllipsisHSolid
            "wolfpackbattalion" -> LineAwesomeIcons.WolfPackBattalion
            "placeofworshipsolid" -> LineAwesomeIcons.PlaceOfWorshipSolid
            "caretleftsolid" -> LineAwesomeIcons.CaretLeftSolid
            "bandaidsolid" -> LineAwesomeIcons.BandAidSolid
            "doorclosedsolid" -> LineAwesomeIcons.DoorClosedSolid
            "supple" -> LineAwesomeIcons.Supple
            "themeco" -> LineAwesomeIcons.Themeco
            "mapsignssolid" -> LineAwesomeIcons.MapSignsSolid
            "drumsteelpanso" -> LineAwesomeIcons.DrumSteelpanSolid
            "addresscardsolid" -> LineAwesomeIcons.AddressCardSolid
            "fastbackwardsolid" -> LineAwesomeIcons.FastBackwardSolid
            "jssquare" -> LineAwesomeIcons.JsSquare
            "headphonesaltsolid" -> LineAwesomeIcons.HeadphonesAltSolid
            "stopcircle" -> LineAwesomeIcons.StopCircle
            "shoppingbasketsolid" -> LineAwesomeIcons.ShoppingBasketSolid
            "teamspeak" -> LineAwesomeIcons.Teamspeak
            "rulerverticalsolid" -> LineAwesomeIcons.RulerVerticalSolid
            "infosolid" -> LineAwesomeIcons.InfoSolid
            "homesolid" -> LineAwesomeIcons.HomeSolid
            "css3" -> LineAwesomeIcons.Css3
            "sleighsolid" -> LineAwesomeIcons.SleighSolid
            "pallet" -> LineAwesomeIcons.PalletSolid
            "haykalsolid" -> LineAwesomeIcons.HaykalSolid
            "sortnumericdownsolid" -> LineAwesomeIcons.SortNumericDownSolid
            "handpointup" -> LineAwesomeIcons.HandPointUp
            "gitkraken" -> LineAwesomeIcons.Gitkraken
            "questioncircle" -> LineAwesomeIcons.QuestionCircle
            "neos" -> LineAwesomeIcons.Neos
            "restroomsolid" -> LineAwesomeIcons.RestroomSolid
            "lemonsolid" -> LineAwesomeIcons.LemonSolid
            "checksquare" -> LineAwesomeIcons.CheckSquare
            "reddit" -> LineAwesomeIcons.Reddit
            "intercom" -> LineAwesomeIcons.Intercom
            "liferingsolid" -> LineAwesomeIcons.LifeRingSolid
            "yandex" -> LineAwesomeIcons.Yandex
            "chairsolid" -> LineAwesomeIcons.ChairSolid
            "downloadsolid" -> LineAwesomeIcons.DownloadSolid
            "cutsolid" -> LineAwesomeIcons.CutSolid
            "gitter" -> LineAwesomeIcons.Gitter
            "chalkboardsolid" -> LineAwesomeIcons.ChalkboardSolid
            "wpforms" -> LineAwesomeIcons.Wpforms
            "hourglasshalfsolid" -> LineAwesomeIcons.HourglassHalfSolid
            "calendarcheck" -> LineAwesomeIcons.CalendarCheck
            "kisswinkheartsolid" -> LineAwesomeIcons.KissWinkHeartSolid
            "truckmonstersolid" -> LineAwesomeIcons.TruckMonsterSolid
            "fingerprintsolid" -> LineAwesomeIcons.FingerprintSolid
            "recyclesolid" -> LineAwesomeIcons.RecycleSolid
            "mehsolid" -> LineAwesomeIcons.MehSolid
            "grinsolid" -> LineAwesomeIcons.GrinSolid
            "flag" -> LineAwesomeIcons.Flag
            "chartbarsolid" -> LineAwesomeIcons.ChartBarSolid
            "ribbonsolid" -> LineAwesomeIcons.RibbonSolid
            "poostorms" -> LineAwesomeIcons.PooStormSolid
            "objectungroup" -> LineAwesomeIcons.ObjectUngroup
            "windowminimize" -> LineAwesomeIcons.WindowMinimize
            "hamburgersolid" -> LineAwesomeIcons.HamburgerSolid
            "chartareasolid" -> LineAwesomeIcons.ChartAreaSolid
            "applealtsolid" -> LineAwesomeIcons.AppleAltSolid
            "atomsolid" -> LineAwesomeIcons.AtomSolid
            "google" -> LineAwesomeIcons.Google
            "getpocket" -> LineAwesomeIcons.GetPocket
            "paintbrushsolid" -> LineAwesomeIcons.PaintBrushSolid
            "poopsolid" -> LineAwesomeIcons.PoopSolid
            "fansolid" -> LineAwesomeIcons.FanSolid
            "clone" -> LineAwesomeIcons.Clone
            "rocketchat" -> LineAwesomeIcons.Rocketchat
            "stackexchange" -> LineAwesomeIcons.StackExchange
            "shuttlevansolid" -> LineAwesomeIcons.ShuttleVanSolid
            "walkingsolid" -> LineAwesomeIcons.WalkingSolid
            "usershieldsolid" -> LineAwesomeIcons.UserShieldSolid
            "cloudmoonsolid" -> LineAwesomeIcons.CloudMoonSolid
            "bootstrap" -> LineAwesomeIcons.Bootstrap
            "venusdoublesolid" -> LineAwesomeIcons.VenusDoubleSolid
            "gulp" -> LineAwesomeIcons.Gulp
            "envelope" -> LineAwesomeIcons.Envelope
            "snapchatghost" -> LineAwesomeIcons.SnapchatGhost
            "mdb" -> LineAwesomeIcons.Mdb
            "moneycheckaltsolid" -> LineAwesomeIcons.MoneyCheckAltSolid
            "handlizardsolid" -> LineAwesomeIcons.HandLizardSolid
            "swimmingpoolsolid" -> LineAwesomeIcons.SwimmingPoolSolid
            "grunt" -> LineAwesomeIcons.Grunt
            "stumbleupon" -> LineAwesomeIcons.Stumbleupon
            "mousepointersolid" -> LineAwesomeIcons.MousePointerSolid
            "optinmonster" -> LineAwesomeIcons.OptinMonster
            "futbolsolid" -> LineAwesomeIcons.FutbolSolid
            "fastforwardsolid" -> LineAwesomeIcons.FastForwardSolid
            "criticalrole" -> LineAwesomeIcons.CriticalRole
            "expeditedssl" -> LineAwesomeIcons.Expeditedssl
            "aligncentersolid" -> LineAwesomeIcons.AlignCenterSolid
            "tabletssolid" -> LineAwesomeIcons.TabletsSolid
            "hubspot" -> LineAwesomeIcons.Hubspot
            "filepowerpoint" -> LineAwesomeIcons.FilePowerpoint
            "dizzysolid" -> LineAwesomeIcons.DizzySolid
            "filealtsolid" -> LineAwesomeIcons.FileAltSolid
            "weighthangings" -> LineAwesomeIcons.WeightHangingSolid
            "helicopters" -> LineAwesomeIcons.HelicopterSolid
            "angleright" -> LineAwesomeIcons.AngleRightSolid
            "arrowaltcircleupsolid" -> LineAwesomeIcons.ArrowAltCircleUpSolid
            "folderopensolid" -> LineAwesomeIcons.FolderOpenSolid
            "globesolid" -> LineAwesomeIcons.GlobeSolid
            "locationarrowsolid" -> LineAwesomeIcons.LocationArrowSolid
            "memorysolid" -> LineAwesomeIcons.MemorySolid
            "fileimagesolid" -> LineAwesomeIcons.FileImageSolid
            "blackberry" -> LineAwesomeIcons.Blackberry
            "tabletaltsolid" -> LineAwesomeIcons.TabletAltSolid
            "khandasolid" -> LineAwesomeIcons.KhandaSolid
            "pollhsolid" -> LineAwesomeIcons.PollHSolid
            "mediumm" -> LineAwesomeIcons.MediumM
            "edit" -> LineAwesomeIcons.Edit
            "femalesolid" -> LineAwesomeIcons.FemaleSolid
            "globeasiasolid" -> LineAwesomeIcons.GlobeAsiaSolid
            "symfony" -> LineAwesomeIcons.Symfony
            "pizzaslicesolid" -> LineAwesomeIcons.PizzaSliceSolid
            "camerasolid" -> LineAwesomeIcons.CameraSolid
            "lifering" -> LineAwesomeIcons.LifeRing
            "masksolid" -> LineAwesomeIcons.MaskSolid
            "ccjcb" -> LineAwesomeIcons.CcJcb
            "creativecommons" -> LineAwesomeIcons.CreativeCommonsPd
            "funneldollarsolid" -> LineAwesomeIcons.FunnelDollarSolid
            "guitarsolid" -> LineAwesomeIcons.GuitarSolid
            "comments" -> LineAwesomeIcons.Comments
            "thumbsupsolid" -> LineAwesomeIcons.ThumbsUpSolid
            "caretsquareleft" -> LineAwesomeIcons.CaretSquareLeft
            "evernote" -> LineAwesomeIcons.Evernote
            "prescriptionsolid" -> LineAwesomeIcons.PrescriptionSolid
            "layergroupsolid" -> LineAwesomeIcons.LayerGroupSolid
            "anchorsolid" -> LineAwesomeIcons.AnchorSolid
            "windowrestoresolid" -> LineAwesomeIcons.WindowRestoreSolid
            "behancesquare" -> LineAwesomeIcons.BehanceSquare
            "handrock" -> LineAwesomeIcons.HandRock
            "ccmastercard" -> LineAwesomeIcons.CcMastercard
            "handshake" -> LineAwesomeIcons.Handshake
            "blogsolid" -> LineAwesomeIcons.BlogSolid
            "snowflakesolid" -> LineAwesomeIcons.SnowflakeSolid
            "usermdsolid" -> LineAwesomeIcons.UserMdSolid
            "flasksolid" -> LineAwesomeIcons.FlaskSolid
            "glassmartini" -> LineAwesomeIcons.GlassMartiniAltSolid
            "microphonealtslashsolid" -> LineAwesomeIcons.MicrophoneAltSlashSolid
            "arrowsaltvsolid" -> LineAwesomeIcons.ArrowsAltVSolid
            "expandsolid" -> LineAwesomeIcons.ExpandSolid
            "accusoft" -> LineAwesomeIcons.Accusoft
            "envira" -> LineAwesomeIcons.Envira
            "suitcaserollings" -> LineAwesomeIcons.SuitcaseRollingSolid
            "blindsolid" -> LineAwesomeIcons.BlindSolid
            "crosssolid" -> LineAwesomeIcons.CrossSolid
            "shapessolid" -> LineAwesomeIcons.ShapesSolid
            "sortalphaupsolid" -> LineAwesomeIcons.SortAlphaUpSolid
            "soundcloud" -> LineAwesomeIcons.Soundcloud
            "stickynote" -> LineAwesomeIcons.StickyNoteSolid
            "horseheadsolid" -> LineAwesomeIcons.HorseHeadSolid
            "invision" -> LineAwesomeIcons.Invision
            "flagcheckeredsolid" -> LineAwesomeIcons.FlagCheckeredSolid
            "batteryquartersolid" -> LineAwesomeIcons.BatteryQuarterSolid
            "sortamountupsolid" -> LineAwesomeIcons.SortAmountUpSolid
            "truckmovings" -> LineAwesomeIcons.TruckMovingSolid
            "chesssolid" -> LineAwesomeIcons.ChessSolid
            "codepen" -> LineAwesomeIcons.Codepen
            "toothsolid" -> LineAwesomeIcons.ToothSolid
            "commentalt" -> LineAwesomeIcons.CommentAlt
            "redoaltsolid" -> LineAwesomeIcons.RedoAltSolid
            else -> Icons.Default.Close
        }

        Icon(iconType, null)
    }
}