package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.exceptions.CannotAccessFileException;
import org.example.app.exceptions.DownloadsPageException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@RequestMapping("/files")
@Controller
public class DownloadsController {

    private final Logger logger = Logger.getLogger(DownloadsController.class);

    @GetMapping("/list")
    public String getUploadsPage(Model model) throws DownloadsPageException {
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(
                rootPath + File.separator + "externalUploads");

        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();

            logger.info("got the externalUploads with size " + files.length);

            model.addAttribute("files", files);
            model.addAttribute("message",files.length + " file(s) on the list");
            for (File file : files) {
                model.addAttribute("file", file);
                logger.info("found a file " + file.getName() + " at " + file.getAbsolutePath());
            }
            return "uploads_page";
        } else {
            logger.info("cannot find the directory with downloads");
            throw new DownloadsPageException("Directory  doesn't exist");
        }
    }


    @GetMapping("/{fileName}")
    public String downloadFile(@PathVariable("fileName") String fileName, Model model) throws CannotAccessFileException {

        model.addAttribute("file", fileName);

        byte[] bytes = this.readFileFromServer(fileName);

        this.writeToLocalFile(fileName, bytes, model);
        logger.info("done");

        return "redirect:/files/success";
    }


    private byte[] readFileFromServer(String fileName) throws CannotAccessFileException {
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(
                rootPath + File.separator + "externalUploads");

        Path serverFilePath = Paths.get(String.valueOf(dir), fileName);
        logger.info("trying to access the file at " + serverFilePath.toString());

        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(serverFilePath);
            return bytes;
        } catch (IOException e) {
            throw new CannotAccessFileException("The file cannot be read!");
        }
    }

    private void writeToLocalFile(String fileName, byte[] bytes, Model model) throws CannotAccessFileException {
        File targetDir = new File("C:\\Downloads\\externalDownloads\\");
        if (!targetDir.exists()) {
            targetDir.mkdir();
        }

        File target = new File(targetDir.getPath() + File.separator
                + fileName);
        logger.info("writing the file to " + target.toPath());
        model.addAttribute("target", target.toPath().toString());

        try {
            Files.write(target.toPath(), bytes, StandardOpenOption.CREATE);
//            Files.write(target.toPath(), bytes, StandardOpenOption.CREATE_NEW);//uncomment this line and comment the previous to check exception handlining
        } catch (IOException e) {
            throw new CannotAccessFileException("Cannot write to this file! " +
                    "Probably, a file with the same name already exists in the downloads directory.");
        }
    }

    @ExceptionHandler(CannotAccessFileException.class)
    public String handleError(Model model, CannotAccessFileException e) {
        model.addAttribute("errorMessage", e.getMessage());
        return "/cannot_access_file";
    }

    @GetMapping("/success")
    public String getSuccessPage(@RequestParam(value = "file", required = false) String file,
                                 @RequestParam(value = "fileToRemove", required = false) String fileToRemove,
                                 @RequestParam(value = "target", required = false) String target, Model model) {
        if (target == null) {
            model.addAttribute("message", "File " + fileToRemove + " has been successfully removed from the server");
        } else {
            model.addAttribute("message", "File " + file + " has been successfully downloaded to \n" +
                    target);
        }
        return "file_operation_success";
    }

    @PostMapping("/delete/{fileName}")
    public String removeFileFromServer(@PathVariable("fileName") String fileName, Model model) throws CannotAccessFileException, DownloadsPageException {
        logger.info(" trying to remove fileName " + fileName);
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(
                rootPath + File.separator + "externalUploads");
        logger.info(" trying to remove fileName " + fileName + " from " + dir.getAbsolutePath());

        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();

            for (File f : files) {
                if (f.getName().equals(fileName)) {
                    if (f.delete()) {
                        model.addAttribute("fileToRemove", fileName);
                        logger.info("file " + fileName + " was successfully removed");
                    } else throw new CannotAccessFileException("The file cannot be removed!");
                }
            }
        }  else {
            logger.info("cannot find the directory with downloads");
            throw new DownloadsPageException("Directory  doesn't exist");
        }
        return "redirect:/files/success";
    }
}
